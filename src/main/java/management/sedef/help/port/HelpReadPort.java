package management.sedef.help.port;

import management.sedef.help.model.Help;
import management.sedef.help.model.enums.HelpStatus;

import java.util.List;
import java.util.Optional;

public interface HelpReadPort {
    Optional<Help> findById(String id);
    List<Help> findAll();
    List<Help> findByProjectId(Integer projectId);
    List<Help> findByProjectIdAndHelpStatus(Integer projectId, HelpStatus helpStatus);
}
