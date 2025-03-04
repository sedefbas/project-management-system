package management.sedef.help.port.adapter;


import lombok.RequiredArgsConstructor;
import management.sedef.help.exception.HelpCommentNotFoundException;
import management.sedef.help.model.HelpComment;
import management.sedef.help.model.document.HelpCommentDocument;
import management.sedef.help.model.mapper.HelpCommentToDocumentMapper;
import management.sedef.help.model.mapper.HelpCommentToDomainMapper;
import management.sedef.help.port.HelpCommentDeletePort;
import management.sedef.help.port.HelpCommentReadPort;
import management.sedef.help.port.HelpCommentSavePort;
import management.sedef.help.repository.HelpCommentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HelpCommentAdapter implements HelpCommentDeletePort, HelpCommentReadPort, HelpCommentSavePort {

    private final HelpCommentRepository helpCommentRepository;
    private final HelpCommentToDocumentMapper helpCommentToDocumentMapper = HelpCommentToDocumentMapper.initialize();
    private final HelpCommentToDomainMapper helpCommentToDomainMapper = HelpCommentToDomainMapper.initialize();

    @Override
    public Optional<HelpComment> findById(String id) {
        return helpCommentRepository.findById(id)
                .map(helpCommentToDomainMapper::map);
    }

    @Override
    public List<HelpComment> findAll() {
        return helpCommentRepository.findAll().stream()
                .map(helpCommentToDomainMapper::map)
                .collect(Collectors.toList());
    }
    @Override
    public List<HelpComment> getCommentsByHelpId(String helpId) {
        List<HelpCommentDocument> commentDocuments = helpCommentRepository.findByHelpId(helpId);
        return commentDocuments.stream()
                .map(helpCommentToDomainMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public HelpComment deleteById(String id) {
        return helpCommentRepository.findById(id)
                .map(helpCommentDocument -> {
                    HelpComment helpComment = helpCommentToDomainMapper.map(helpCommentDocument);
                    helpCommentRepository.deleteById(id);
                    return helpComment;
                })
                .orElseThrow(() -> new HelpCommentNotFoundException("HelpComment not found with id: " + id));
    }

    @Override
    public HelpComment save(HelpComment helpComment) {
        HelpCommentDocument document = helpCommentToDocumentMapper.map(helpComment);
        return helpCommentToDomainMapper.map(helpCommentRepository.save(document));
    }
}
