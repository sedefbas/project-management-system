package management.sedef.help.service;

import management.sedef.help.model.Help;
import management.sedef.help.model.HelpComment;
import management.sedef.help.repository.HelpRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HelpServiceImpl implements HelpService {

    private final HelpRepository helpRepository;

    @Override
    public List<Help> getHelpsByProjectId(Integer projectId) {
        return helpRepository.findByProjectId(projectId);
    }

    @Override
    public Help createHelp(Help help) {
        return helpRepository.save(help);
    }

    @Override
    public Help getHelpById(String helpId) {
        return helpRepository.findById(helpId).orElseThrow(() -> 
            new RuntimeException("Help not found with ID: " + helpId)
        );
    }

    @Override
    public Help addCommentToHelp(String helpId, HelpComment comment) {
        Help help = getHelpById(helpId);
        help.getComments().add(comment);
        return helpRepository.save(help);
    }

    @Override
    public void deleteHelp(String helpId) {
        helpRepository.deleteById(helpId);
    }

}
