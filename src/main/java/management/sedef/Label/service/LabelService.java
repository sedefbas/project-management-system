package management.sedef.Label.service;

import management.sedef.Label.model.Label;
import management.sedef.Label.model.request.LabelRequest;

import java.util.List;

public interface LabelService {

    List<Label> findLabelsByCompanyId(Long companyId);
    void save(LabelRequest request);
    void delete(Long labelId);
}
