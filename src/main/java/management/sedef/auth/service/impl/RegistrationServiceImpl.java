package management.sedef.auth.service.impl;


import lombok.RequiredArgsConstructor;
import management.sedef.auth.exception.*;
import management.sedef.auth.model.Role;
import management.sedef.auth.model.request.RegisterRequest;
import management.sedef.auth.model.request.VerifyRequest;
import management.sedef.auth.port.RoleReadPort;
import management.sedef.auth.service.RegistrationService;
import management.sedef.user.model.User;
import management.sedef.user.model.UserVerification;
import management.sedef.user.model.enums.UserStatus;
import management.sedef.user.model.enums.UserVerificationStatus;
import management.sedef.user.model.enums.UserVerificationType;
import management.sedef.user.port.UserReadPort;
import management.sedef.user.port.UserSavePort;
import management.sedef.user.port.UserVerificationReadPort;
import management.sedef.user.port.UserVerificationSavePort;
import management.sedef.user.service.UserEmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
class RegistrationServiceImpl implements RegistrationService {

    private final UserReadPort userReadPort;
    private final UserSavePort userSavePort;
    private final RoleReadPort roleReadPort;
    private final PasswordEncoder passwordEncoder;
    private final UserEmailService userEmailService;
    private final UserVerificationReadPort userVerificationReadPort;
    private final UserVerificationSavePort userVerificationSavePort;


    @Override
    @Transactional
    public void register(RegisterRequest request) {

        if (userReadPort.existsByEmail(request.getEmail())) {
            throw new UserAlreadyRegisteredException(request.getEmail());
        }
      //rolname ya member ya compnayowner olacak.
        Role role = roleReadPort.findByName(request.getRoleName())
                .orElseThrow(() -> new RoleNotFoundByNameException(request.getRoleName().name()));

        if (!role.validateMemberOrCompanyOwnerRole()) {
            throw new InvalidRoleException("Invalid role. Only MEMBER or COMPANY_OWNER roles are allowed.");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .status(UserStatus.NOT_VERIFIED)
                .role(role)
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userSavePort.save(user);

        UserVerification userVerification = UserVerification.builder()
                .user(savedUser)
                .status(UserVerificationStatus.WAITING)
                .type(UserVerificationType.EMAIL)
                .build();

        UserVerification savedUserVerification = userVerificationSavePort.save(userVerification);


        userEmailService.sendVerification(savedUser.getEmail(), savedUserVerification.getId());
    }

    @Override
    @Transactional
    public void verifyMail(VerifyRequest verifyRequest) {

        UserVerification userVerification = userVerificationReadPort
                .findByTypeAndId(UserVerificationType.EMAIL,verifyRequest.getVerificationId())
                .orElseThrow(() -> new UserVerificationIsNotFoundException(verifyRequest.getVerificationId()));

        if (userVerification.isCompleted()) {
            throw new UserVerificationAlreadyCompletedException();
        }

        userVerification.complete();
        userVerificationSavePort.save(userVerification);


        User user = userReadPort.findById(userVerification.getUser().getId())
                .orElseThrow(() -> new UserNotFoundByIdException(userVerification.getUser().getId()));

        user.verify();
        userSavePort.save(user);

        userEmailService.sendWelcome(user.getEmail());
    }
}
