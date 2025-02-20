package management.sedef.issue.Service;


import management.sedef.issue.model.request.IssueAssignmentRequest;

public interface IssueAssignmentService {

    void addIssueAssignment(IssueAssignmentRequest request, String token);
    void delete(Long issueId, Long userId);


    //todo issue ıd değerine göre tüm atananlari listeleme.
    //todo kullanıcının hangi görevlere atandığını listeleme. burada ek olarak proje id ye göre de olabilir.
    //todo kullanıcının rolünü güncelleme


}
