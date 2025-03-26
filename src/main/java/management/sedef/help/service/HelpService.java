package management.sedef.help.service;
import management.sedef.help.model.Help;
import management.sedef.help.model.enums.HelpStatus;
import management.sedef.help.model.request.HelpRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public interface HelpService {

    List<Help> getHelpsByProjectId(Integer projectId);

    List<Help> getHelpsByProjectIdAndStatus(Integer projectId, HelpStatus helpStatus); // Yeni method

    Help createHelp(HelpRequest request, MultipartFile photo);

    Help getHelpById(String helpId);

    void deleteHelp(String helpId);
}
