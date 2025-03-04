package management.sedef.help.port;

import management.sedef.help.model.HelpComment;

public interface HelpCommentDeletePort {
    HelpComment deleteById(String id);

}
