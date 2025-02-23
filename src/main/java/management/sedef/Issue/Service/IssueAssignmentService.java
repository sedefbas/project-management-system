package management.sedef.issue.Service;


import java.util.List;
import java.util.Map;       

import management.sedef.auth.model.enums.RoleName;
import management.sedef.issue.model.dto.AssignedUserDTO;
import management.sedef.issue.model.enums.IssueAssignmentType;
import management.sedef.issue.model.request.IssueAssignmentRequest;

public interface IssueAssignmentService {

    void addIssueAssignment(IssueAssignmentRequest request, String token);
    void delete(Long issueId, Long userId);
    void updateRole(Long issueId, Long userId,RoleName roleName);
    Map<IssueAssignmentType, List<AssignedUserDTO>> getAssignedUsersByIssueId(Long issueId);


    //todo issue ıd değerine göre tüm atananlari listeleme.
    //todo kullanıcının hangi görevlere atandığını listeleme. burada ek olarak proje id ye göre de olabilir.



}
