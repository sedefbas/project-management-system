package management.sedef.issue.Service;


import java.util.List;
import java.util.Map;       

import management.sedef.auth.model.enums.RoleName;
import management.sedef.issue.model.IssueAssignment;
import management.sedef.issue.model.dto.AssignedUserDTO;
import management.sedef.issue.model.enums.IssueAssignmentType;
import management.sedef.issue.model.request.IssueAssignmentRequest;

public interface IssueAssignmentService {

    void addIssueAssignment(IssueAssignmentRequest request, String token);
    void delete(Long issueId, Long userId);
    void updateRole(Long issueId, Long userId,RoleName roleName);
    Map<IssueAssignmentType, List<AssignedUserDTO>> getAssignedUsersByIssueId(Long issueId);
    List<IssueAssignment> getAssignmentsByUserIdAndProjectId(Long userId, Long projectId);
    IssueAssignment findById(Long id);


    //todo kullanıcının hangi görevlere atandığını proje id ye göre  listeme



}
