package management.sedef.Label.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import management.sedef.Label.model.Label;
import management.sedef.Label.model.mapper.label.LabelRequestToDomainMapper;
import management.sedef.Label.model.request.LabelRequest;
import management.sedef.Label.port.LabelPort.LabelDeletePort;
import management.sedef.Label.port.LabelPort.LabelReadPort;
import management.sedef.Label.port.LabelPort.LabelSavePort;
import management.sedef.Label.service.LabelService;
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
