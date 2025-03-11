package management.sedef.label.port.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import management.sedef.label.exception.LabelNotFoundException;
import management.sedef.label.model.Label;
import management.sedef.label.model.entity.LabelEntity;
import management.sedef.label.model.mapper.label.LabelEntityToDomainMapper;
import management.sedef.label.model.mapper.label.LabelToLabelEntityMapper;
import management.sedef.label.port.LabelPort.LabelDeletePort;
import management.sedef.label.port.LabelPort.LabelReadPort;
import management.sedef.label.port.LabelPort.LabelSavePort;
import management.sedef.label.repository.LabelRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Slf4j
public class LabelAdapter implements LabelReadPort, LabelSavePort, LabelDeletePort {

    private final LabelRepository repository;
    private final LabelEntityToDomainMapper labelEntityToDomainMapper = LabelEntityToDomainMapper.initialize();
    private final LabelToLabelEntityMapper labelToLabelEntityMapper = LabelToLabelEntityMapper.initialize();


    @Override
    public List<Label> findLabelsByCompanyId(Long companyId) {

        List<LabelEntity> labelEntities = repository.findLabelsByCompanyId(companyId)
                .orElseThrow(() -> new LabelNotFoundException("label not found"));

        List<Label> labels = labelEntities.stream().map(labelEntity -> {
            Label label = labelEntityToDomainMapper.map(labelEntity);

            label.setIsDefault(labelEntity.getIsDefault());
            return label;
        }).collect(Collectors.toList());
        return labels;
    }


    @Override
    public Label findById(Long labelId) {
        return repository.findById(labelId)
                .map(labelEntityToDomainMapper::map)
                .orElseThrow(() -> new LabelNotFoundException("Label not found for ID: " + labelId));
    }



    @Override
    public Label save(Label label) {
        LabelEntity labelEntity = labelToLabelEntityMapper.map(label);
        labelEntity.setIsDefault(label.getIsDefault());
        repository.save(labelEntity);
        return labelEntityToDomainMapper.map(labelEntity);
    }

    @Override
    public void delete(Label label) {
        final LabelEntity labelEntity = labelToLabelEntityMapper.map(label);
        repository.delete(labelEntity);
    }
}
