package management.sedef.label.port.LabelPort;

import management.sedef.label.model.Label;


import java.util.List;

public interface LabelReadPort {
    List<Label> findLabelsByCompanyId( Long companyId);
    Label findById(Long labelId);

}
