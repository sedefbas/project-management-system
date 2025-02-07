package management.sedef.common.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MailTemplate {

    USER_VERIFICATION("user-verification.html"),
    USER_WELCOME("user-welcome.html"),
    PROJECT_INVITATION("project-invitation.html"),
    COMPANY_INVITATION("company-invitation.html"),
    REGISTER_INVITATION("register-invitation.html");

    private final String file;

}