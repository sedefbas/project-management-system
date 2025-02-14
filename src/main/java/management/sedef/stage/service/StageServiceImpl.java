package management.sedef.stage.service;


import lombok.RequiredArgsConstructor;
import management.sedef.stage.model.Mapper.StageEntityToDomainMapper;
import management.sedef.stage.model.Stage;
import management.sedef.stage.model.enums.StageType;
import management.sedef.stage.port.StageReadPort;
import management.sedef.stage.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StageServiceImpl implements StageService{

    private final StageReadPort readPort;

    @Override
    public List<Stage> findAll() {
        return readPort.findAll();
    }

    @Override
    public Stage findByName(StageType name) {
        return readPort.findByName(name);
    }

    @Override
    public Stage findById(Long stageId) {
        return readPort.findById(stageId);
    }

}
