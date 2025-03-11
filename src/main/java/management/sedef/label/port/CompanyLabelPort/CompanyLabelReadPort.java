package management.sedef.label.port.CompanyLabelPort;

import management.sedef.label.model.CompanyLabel;


public interface CompanyLabelReadPort {

    CompanyLabel findByCompanyIdAndLabelId(Long companyId, Long labelId);

}