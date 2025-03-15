package management.sedef.issue.Service.ımpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import management.sedef.auth.exception.RoleNotFoundByNameException;
import management.sedef.auth.model.Role;
import management.sedef.auth.model.enums.RoleName;
import management.sedef.auth.port.RoleReadPort;
import management.sedef.issue.Service.IssueAssignmentService;
import management.sedef.issue.kafka.event.EventType;
import management.sedef.issue.kafka.event.IssueMailEvent;
import management.sedef.issue.kafka.producer.IssueMailProducer;
import management.sedef.issue.kafka.producer.IssueUserProducer;
import management.sedef.issue.kafka.event.IssueUserEvent;
import management.sedef.issue.model.IssueAssignment;
import management.sedef.issue.model.dto.AssignedUserDTO;
import management.sedef.issue.model.enums.IssueAssignmentType;
import management.sedef.issue.model.mapper.issueAssignment.IssueAssignmentRequestToDomainMapper;
import management.sedef.issue.model.request.IssueAssignmentRequest;
import management.sedef.issue.port.IssueAssignmentPort.IssueAssignmentDeletePort;
import management.sedef.issue.port.IssueAssignmentPort.IssueAssignmentReadPort;
import management.sedef.issue.port.IssueAssignmentPort.IssueAssignmentSavePort;
import management.sedef.user.model.User;
import management.sedef.user.service.UserEmailService;
import management.sedef.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class IssueAssignmentImpl implements IssueAssignmentService {

    private final IssueAssignmentDeletePort deletePort;
    private final IssueAssignmentReadPort readPort;
    private final IssueAssignmentSavePort savePort;
    private final UserService userService;
    private final IssueAssignmentRequestToDomainMapper assignmentRequestToDomainMapper;
    private final RoleReadPort roleReadPort;
    private final IssueUserProducer issueProducer;
    private final IssueMailProducer issueMailProducer;



    //todo kayıtlıysa tekrar kaydetmemeli.
    @Override
    @Transactional
    public void addIssueAssignment(IssueAssignmentRequest request, String token) {
        User assignedBy = userService.getUserFromToken(token);

        Role role = roleReadPort.findByName(request.getRole())
                .orElseThrow(() -> new RoleNotFoundByNameException(request.getRole().name()));

        IssueAssignment issueAssignment = assignmentRequestToDomainMapper.map(request);
        issueAssignment.setAssignedBy(assignedBy);
        issueAssignment.setRole(role);

        IssueAssignment savedIssueAssignment = savePort.save(issueAssignment);

        IssueUserEvent issueUserEvent = IssueUserEvent.builder()
                .issueAssignmentId(savedIssueAssignment.getId())
                .issueId(savedIssueAssignment.getIssue().getId())
                .assignedUserId(savedIssueAssignment.getAssignedUser().getId())
                .assignmentDate(savedIssueAssignment.getAssignmentDate())
                .assignedById(savedIssueAssignment.getAssignedBy().getId())
                .role(savedIssueAssignment.getRole())
                .type(EventType.ISSUE_ASSIGNED_TO_USER)
                .build();

        IssueMailEvent issueMailEvent = IssueMailEvent.builder()
                .issueAssignmentId(savedIssueAssignment.getId())
                .build();

        issueMailProducer.sendMessage(issueMailEvent);

        issueProducer.sendMessage(issueUserEvent);
    }


    @Override
    public void delete(Long issueId, Long userId) {
        IssueAssignment issueAssignment = readPort.findByIssueIdAndAssignedUserId(issueId,userId);
        deletePort.delete(issueAssignment);

        IssueUserEvent issueUserEvent = IssueUserEvent.builder()
                .issueAssignmentId(issueAssignment.getId())
                .issueId(issueAssignment.getIssue().getId())
                .assignedUserId(issueAssignment.getAssignedUser().getId())
                .assignmentDate(issueAssignment.getAssignmentDate())
                .assignedById(issueAssignment.getAssignedBy().getId())
                .role(issueAssignment.getRole())
                .type(EventType.ISSUE_UNASSIGNED_FROM_USER)
                .build();

        issueProducer.sendMessage(issueUserEvent);
    }

    @Override
    public void updateRole(Long issueId, Long userId, RoleName roleName) {
        IssueAssignment issueAssignment = readPort.findByIssueIdAndAssignedUserId(issueId,userId);
        Optional<Role> role = roleReadPort.findByName(roleName);
        issueAssignment.setRole(role.get());
        savePort.save(issueAssignment);


        IssueUserEvent issueUserEvent = IssueUserEvent.builder()
                .issueAssignmentId(issueAssignment.getId())
                .issueId(issueAssignment.getIssue().getId())
                .assignedUserId(issueAssignment.getAssignedUser().getId())
                .assignmentDate(issueAssignment.getAssignmentDate())
                .assignedById(issueAssignment.getAssignedBy().getId())
                .role(issueAssignment.getRole())
                .type(EventType.ISSUE_ROLE_UPDATED)
                .build();

        issueProducer.sendMessage(issueUserEvent);
    }

    @Override
    public Map<IssueAssignmentType, List<AssignedUserDTO>> getAssignedUsersByIssueId(Long issueId) {
        List<IssueAssignment> assignments = readPort.findAllByIssueId(issueId);
        
        Map<IssueAssignmentType, List<AssignedUserDTO>> assignedUsers = new HashMap<>();
        
        for (IssueAssignment assignment : assignments) {
            IssueAssignmentType type = IssueAssignmentType.valueOf(assignment.getRole().getName());
            
            AssignedUserDTO userDTO = AssignedUserDTO.builder()
                    .userId(assignment.getAssignedUser().getId())
                    .firstName(assignment.getAssignedUser().getFirstName())
                    .lastName(assignment.getAssignedUser().getLastName())
                    .roleName(RoleName.valueOf(assignment.getRole().getName()))
                    .build();
                    
            assignedUsers.computeIfAbsent(type, k -> new ArrayList<>())
                        .add(userDTO);
        }
        
        return assignedUsers;
    }

    @Override
    public List<IssueAssignment> getAssignmentsByUserIdAndProjectId(Long userId, Long projectId) {
        return readPort.findByAssignedUserIdAndProjectId(userId, projectId);
    }

    @Override
    public IssueAssignment findById(Long id) {
        return readPort.findById(id);
    }


}
