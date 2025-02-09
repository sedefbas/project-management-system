package management.sedef.Label.port.CompanyLabelPort;

import management.sedef.Label.model.CompanyLabel;
import management.sedef.Label.model.Label;
import java.util.List;



public interface CompanyLabelReadPort {

    CompanyLabel findByCompanyIdAndLabelId(Long companyId, Long labelId);

}