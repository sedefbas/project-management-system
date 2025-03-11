package management.sedef.label.service;

import management.sedef.label.model.request.CompanyLabelRequest;

public interface CompanylabelService {
   void addLabelToCompany(Long companyId, CompanyLabelRequest request);
   void removeLabelFromCompany(Long companyId, Long labelId);

}
