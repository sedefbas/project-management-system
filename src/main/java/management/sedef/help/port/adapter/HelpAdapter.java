package management.sedef.help.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.help.exception.HelpNotFoundException;
import management.sedef.help.model.Help;
import management.sedef.help.model.document.HelpDocument;
import management.sedef.help.model.enums.HelpStatus;
import management.sedef.help.model.mapper.HelpDocumentToDomainMapper;
import management.sedef.help.model.mapper.HelpToDocumentMapper;
import management.sedef.help.port.HelpDeletePort;
import management.sedef.help.port.HelpReadPort;
import management.sedef.help.port.HelpSavePort;
import management.sedef.help.repository.HelpRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HelpAdapter implements HelpReadPort, HelpDeletePort, HelpSavePort {

    private final HelpRepository helpRepository;
    private final HelpDocumentToDomainMapper helpDocumentToDomainMapper = HelpDocumentToDomainMapper.initialize();
    private final HelpToDocumentMapper helpToDocumentMapper = HelpToDocumentMapper.initialize();

    @Override
    public Optional<Help> findById(String id) {
        return helpRepository.findById(id)
                .map(helpDocumentToDomainMapper::map);
    }

    @Override
    public List<Help> findAll() {
        return helpRepository.findAll().stream()
                .map(helpDocumentToDomainMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<Help> findByProjectId(Integer projectId) {
        return helpRepository.findByProjectId(projectId)
                .stream()
                .map(helpDocumentToDomainMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<Help> findByProjectIdAndHelpStatus(Integer projectId, HelpStatus helpStatus) {
        return helpRepository.findByProjectIdAndHelpstatus(projectId, helpStatus)
                .stream()
                .map(helpDocumentToDomainMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Help deleteById(String id) {
        return helpRepository.findById(id)
                .map(helpDocument -> {
                    Help help = helpDocumentToDomainMapper.map(helpDocument);
                    helpRepository.deleteById(id);
                    return help;
                })
                .orElseThrow(() -> new HelpNotFoundException("Help not found with id: " + id));
    }

    @Override
    public Help save(Help help) {
        HelpDocument document = helpToDocumentMapper.map(help);
        return helpDocumentToDomainMapper.map(helpRepository.save(document));
    }
}
