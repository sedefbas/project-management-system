package management.sedef.priority.service;

import management.sedef.priority.model.request.CompanyPriorityRequest;

public interface CompanyPriorityService {
    void addPriorityToCompany(Long companyId, CompanyPriorityRequest request);
    void removePriorityFromCompany(Long companyId, Long priorityId);
}
