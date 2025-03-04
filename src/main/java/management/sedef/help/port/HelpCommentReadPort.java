package management.sedef.help.port;

import management.sedef.help.model.HelpComment;

import java.util.List;
import java.util.Optional;

public interface HelpCommentReadPort {
    Optional<HelpComment> findById(String id);
    List<HelpComment> findAll();
    List<HelpComment> getCommentsByHelpId(String helpId); // Yorumları getirecek metod

}
