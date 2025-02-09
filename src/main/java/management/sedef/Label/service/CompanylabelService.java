package management.sedef.Label.service;

import management.sedef.Label.model.Label;
import management.sedef.Label.model.request.CompanyLabelRequest;

import java.util.List;

public interface CompanylabelService {
   void addLabelToCompany(Long companyId, CompanyLabelRequest request);
   void removeLabelFromCompany(Long companyId, Long labelId);

}
