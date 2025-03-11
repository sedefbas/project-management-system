package management.sedef.label.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.label.model.Label;
import management.sedef.label.model.mapper.label.LabelRequestToDomainMapper;
import management.sedef.label.model.request.LabelRequest;
import management.sedef.label.port.LabelPort.LabelDeletePort;
import management.sedef.label.port.LabelPort.LabelReadPort;
import management.sedef.label.port.LabelPort.LabelSavePort;
import management.sedef.label.service.LabelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final LabelReadPort labelReadPort;
    private final LabelSavePort labelSavePort;
    private final LabelDeletePort labelDeletePort;
    private final LabelRequestToDomainMapper labelRequestToDomainMapper = LabelRequestToDomainMapper.initialize();


    @Override
    public List<Label> findLabelsByCompanyId(Long companyId) {
        return labelReadPort.findLabelsByCompanyId(companyId);
    }

    @Override
    public void save(LabelRequest request) {
        Label label = labelRequestToDomainMapper.map(request);
        label.setIsDefault(true);
        labelSavePort.save(label);
    }

    @Override
    public void delete(Long labelId) {
       Label label =  labelReadPort.findById(labelId);
        labelDeletePort.delete(label);
    }

    @Override
    public Label findById(Long labelId) {
        return labelReadPort.findById(labelId);
    }

}
