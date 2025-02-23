package management.sedef.user.service;

import management.sedef.issue.model.IssueAssignment;

public interface UserEmailService {

    void sendVerification(String email, Long verificationId);

    void sendRegisterInvitation(String email, Long companyId, String companyName , String token);

    void sendWelcome(String email);

    void sendProjectInvitation(String email, String token, String projectName, String userName );

    void sendCompanyInvitation(String email, Long companyId, String companyName, String userName, String token);

    void reportIssue(IssueAssignment issueAssignment);

}
    