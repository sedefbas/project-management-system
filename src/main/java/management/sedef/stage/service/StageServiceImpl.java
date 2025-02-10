package management.sedef.stage.service;


import lombok.RequiredArgsConstructor;
import management.sedef.stage.model.Mapper.StageEntityToDomainMapper;
import management.sedef.stage.model.Stage;
import management.sedef.stage.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StageServiceImpl implements StageService{

    private final StageRepository repository;
    private final StageEntityToDomainMapper stageEntityToDomainMapper = StageEntityToDomainMapper.initialize();

    @Override
    public List<Stage> findAll() {
        return repository.findAll()
                .stream()
                .map(stageEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }

}
