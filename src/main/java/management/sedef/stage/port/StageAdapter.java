package management.sedef.stage.port;

import lombok.RequiredArgsConstructor;
import management.sedef.stage.exceptions.StageNotFoundException;
import management.sedef.stage.model.Mapper.StageEntityToDomainMapper;
import management.sedef.stage.model.Stage;
import management.sedef.stage.model.entity.StageEntity;
import management.sedef.stage.model.enums.StageType;
import management.sedef.stage.repository.StageRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StageAdapter implements StageReadPort {

    private final StageRepository stageRepository;
    private final StageEntityToDomainMapper stageEntityToDomainMapper = StageEntityToDomainMapper.initialize();

    @Override
    public Stage findByName(StageType name) {
        StageEntity stageEntity = stageRepository.findByName(name)
                .orElseThrow(() -> new StageNotFoundException("Stage not found for type: " + name));
        return stageEntityToDomainMapper.map(stageEntity);
    }

    @Override
    public List<Stage> findAll() {
        return stageRepository.findAll()
                .stream()
                .map(stageEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Stage findById(Long stageId) {
        StageEntity stageEntity = stageRepository.findById(stageId)
                .orElseThrow(() -> new StageNotFoundException("Stage not found for id: " + stageId));
        return stageEntityToDomainMapper.map(stageEntity);
    }

}
