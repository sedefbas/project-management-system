package management.sedef.stage.port;


import management.sedef.stage.model.Stage;
import management.sedef.stage.model.enums.StageType;

import java.util.List;

public interface StageReadPort {
    Stage findByName(StageType name);
    List<Stage> findAll();
    Stage findById(Long stageId);
}
