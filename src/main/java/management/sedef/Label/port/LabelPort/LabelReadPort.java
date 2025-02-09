package management.sedef.Label.port.LabelPort;

import management.sedef.Label.model.Label;


import java.util.List;

public interface LabelReadPort {
    List<Label> findLabelsByCompanyId( Long companyId);
    Label findById(Long labelId);

}
