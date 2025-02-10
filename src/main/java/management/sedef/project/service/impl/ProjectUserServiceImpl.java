package management.sedef.project.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.model.Token;
import management.sedef.auth.model.enums.TokenClaims;
import management.sedef.auth.service.TokenService;
import management.sedef.company.model.Company;
import management.sedef.company.service.CompanyService;
import management.sedef.project.model.Project;
import management.sedef.project.model.ProjectUser;
import management.sedef.project.model.claims.ProjectUserClaims;
import management.sedef.project.model.mapper.projectUser.ProjectUserClaimsToDomainMapper;
import management.sedef.project.model.mapper.projectUser.ProjectUserRequestToDomainMapper;
import management.sedef.project.model.request.ProjectUserRequest;
import management.sedef.project.model.request.ProjectUserUpdateRequest;
import management.sedef.project.port.ProjectUserPort.ProjectUserDeletePort;
import management.sedef.project.port.ProjectUserPort.ProjectUserReadPort;
import management.sedef.project.port.ProjectUserPort.ProjectUserSavePort;
import management.sedef.project.service.ProjectService;
import management.sedef.project.service.ProjectUserService;
import management.sedef.project.validation.ProjectValidator;
import management.sedef.user.exception.UserAlreadyExistsException;
import management.sedef.user.model.User;
import management.sedef.user.port.adapter.UserAdapter;
import management.sedef.user.service.UserEmailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectUserServiceImpl implements ProjectUserService {

    private final TokenService tokenService;
    private final UserEmailService emailService;
    private final ProjectUserSavePort savePort;
    private final ProjectUserReadPort readPort;
    private final ProjectUserDeletePort deletePort;
    private final ProjectService projectService;
    private final CompanyService companyService;
    private final ProjectUserClaimsToDomainMapper projectUserClaimsToDomainMapper;
    private final ProjectUserRequestToDomainMapper projectUserRequestToDomainMapper;
    private final UserAdapter userAdapter;


    public String generateInvitationLink(ProjectUserRequest request,Long projectId, Long companyId) {
        final Claims claims = this.generateClaims(request,projectId,companyId);
        Token token =  tokenService.generate(claims);
        return token.getAccessToken();
    }

    private Claims generateClaims(ProjectUserRequest request, Long projectId, Long companyId) {
        final ClaimsBuilder claimsBuilder = Jwts.claims();

        claimsBuilder.add(TokenClaims.USER_ID.getValue(), request.getUserId());
        claimsBuilder.add(TokenClaims.GROUP_ID.getValue(), request.getGroupId());
        claimsBuilder.add(TokenClaims.USER_ROLE.getValue(),request.getRole().name());
        claimsBuilder.add(TokenClaims.PROJECT_ID.getValue(),projectId);
        claimsBuilder.add(TokenClaims.COMPANY_ID.getValue(),companyId);

        if (request.getSubGroupId() != null) {
            claimsBuilder.add(TokenClaims.SUB_GROUP_ID.getValue(), request.getSubGroupId());
        }

        return claimsBuilder.build();
    }


    @Override
    public void sendUserInvitationForProject(ProjectUserRequest request, Long projectId, Long companyId, String email) {
        Company company = companyService.findCompanyById(companyId);
        int existingUsersCount = readPort.countUsersByProjectId(projectId);

        ProjectValidator.validateMaxUsers(company.getSubscriptionPlan(),existingUsersCount);
        String token = generateInvitationLink(request, projectId, companyId);
        Project project = projectService.findByIdAndCompanyId(projectId,companyId);
        Optional<User> user = userAdapter.findByEmail(email);
        String fullName = user.get().getFirstName() + user.get().getLastName();
        emailService.sendProjectInvitation(email,token,project.getName(),fullName);
    }

    @Override
    public void addUserToProjectByToken(String token) {
        ProjectUserClaims projectUserClaims = tokenService.parseProjectInvitationToken(token);
        ProjectUser projectUser = projectUserClaimsToDomainMapper.map(projectUserClaims);
        checkIfUserAlreadyExists(projectUser);
        savePort.save(projectUser);
    }

    private void checkIfUserAlreadyExists(ProjectUser projectUser) {
        boolean exists = readPort.existsByUserIdAndProjectId(
                projectUser.getUser().getId(),
                projectUser.getProject().getId()
        );

        if (exists) {
            throw new UserAlreadyExistsException("Bu kullanıcı zaten bu projeye eklenmiş.");
        }
    }

    @Override
    public void removeUserFromProjectById(Long userId, Long projectId ) {
        ProjectUser  projectUser = readPort.findByUserIdAndProjectId(userId,projectId);
        deletePort.delete(projectUser);
    }

    @Override
    public void updateUserInProject(ProjectUserUpdateRequest request, Long userId, Long projectId, Long companyId) {
        ProjectUser projectUser = readPort.findByUserIdAndProjectId(userId, projectId);
        ProjectUser projectUserToUpdate = projectUserRequestToDomainMapper.map(request,userId ,projectId, companyId);

        if (projectUserToUpdate.getGroup() != null) {
            projectUser.setGroup(projectUserToUpdate.getGroup());
        }

        if (projectUserToUpdate.getSubGroup() != null) {
            projectUser.setSubGroup(projectUserToUpdate.getSubGroup());
        }

        if (projectUserToUpdate.getRole() != null) {
            projectUser.setRole(projectUserToUpdate.getRole());
        }

        savePort.save(projectUser);
    }


    @Override
    public List<ProjectUser> getUsersForProject(Long projectId ){
        return readPort.findByProjectId(projectId);
    }

    @Override
    public List<ProjectUser> getUsersBySubGroupId(Long subgroup) {
        return readPort.findUsersBySubGroupId(subgroup);
    }

    @Override
    public List<ProjectUser> getUsersBygroupId(Long groupId) {
        return readPort.findUsersByGroupId(groupId);
    }

    @Override
    public List<ProjectUser> getProjectByToken(String token) {
        // "Bearer " kısmını kaldır
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // "Bearer " kısmını atla
        }

        Long userId = tokenService.getUserIdFromToken(token);
        return getProjectsForUser(userId);
    }

    public List<ProjectUser> getProjectsForUser(Long userId) {
        return readPort.findByUserId(userId);
    }


}
