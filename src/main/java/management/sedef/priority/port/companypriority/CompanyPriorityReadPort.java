package management.sedef.priority.port.companypriority;

import management.sedef.priority.model.CompanyPriority;

public interface CompanyPriorityReadPort {
    CompanyPriority findByCompanyIdAndPriorityId(Long companyId, Long priorityId);

}

