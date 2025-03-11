package management.sedef.label.service;

import management.sedef.label.model.Label;
import management.sedef.label.model.request.LabelRequest;

import java.util.List;

public interface LabelService {

    List<Label> findLabelsByCompanyId(Long companyId);
    void save(LabelRequest request);
    void delete(Long labelId);
    Label findById(Long labelId);
}
