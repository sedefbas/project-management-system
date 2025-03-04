package management.sedef.help.service;
import management.sedef.help.model.Help;
import management.sedef.help.model.enums.HelpStatus;
import management.sedef.help.model.request.HelpRequest;

import java.util.List;
public interface HelpService {

    List<Help> getHelpsByProjectId(Integer projectId);

    List<Help> getHelpsByProjectIdAndStatus(Integer projectId, HelpStatus helpStatus); // Yeni method

    Help createHelp(HelpRequest request);

    Help getHelpById(String helpId);

    void deleteHelp(String helpId);
}
