package management.sedef.help.service.ımpl;

import management.sedef.help.exception.HelpCommentNotFoundException;
import management.sedef.help.model.Help;
import management.sedef.help.model.enums.HelpStatus;
import management.sedef.help.model.mapper.HelpRequestToDomainMapper;
import management.sedef.help.model.request.HelpRequest;
import management.sedef.help.port.HelpDeletePort;
import management.sedef.help.port.HelpReadPort;
import management.sedef.help.port.HelpSavePort;
import management.sedef.help.service.HelpService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class HelpServiceImpl implements HelpService {

    private final HelpReadPort readPort;
    private final HelpSavePort savePort;
    private final HelpDeletePort deletePort;
    private final HelpRequestToDomainMapper helpRequestToDomainMapper = HelpRequestToDomainMapper.initialize();

    @Override
    public List<Help> getHelpsByProjectId(Integer projectId) {
        return readPort.findByProjectId(projectId);
    }

    @Override
    public List<Help> getHelpsByProjectIdAndStatus(Integer projectId, HelpStatus helpStatus) {
        return readPort.findByProjectIdAndHelpStatus(projectId, helpStatus);
    }

    @Override
    public Help createHelp(HelpRequest request) {
        Help help = helpRequestToDomainMapper.map(request);
        help.setCreatedAt(LocalDateTime.now()); // Oluşturulma tarihini şu anki zaman olarak ayarla
        return savePort.save(help);
    }

    @Override
    public Help getHelpById(String helpId) {
        return readPort.findById(helpId)
                .orElseThrow(() -> new HelpCommentNotFoundException("Help not found with id: " + helpId));
    }

    @Override
    public void deleteHelp(String helpId) {
        deletePort.deleteById(helpId);
    }
}
