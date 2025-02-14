package management.sedef.stage.service;

import management.sedef.stage.model.Stage;
import management.sedef.stage.model.enums.StageType;

import java.util.List;

public interface StageService {
    List<Stage> findAll();
     Stage findByName(StageType type);
     Stage findById(Long stageId);

    }
