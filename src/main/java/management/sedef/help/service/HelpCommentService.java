package management.sedef.help.service;

import management.sedef.help.model.HelpComment;
import management.sedef.help.model.request.HelpCommentRequest;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Optional;

public interface HelpCommentService {

    Optional<HelpComment> getById(String id);

    List<HelpComment> getCommentsByHelpId(String helpId);

    HelpComment addCommentToHelp(HelpCommentRequest comment);

    void deleteComment(String commentId);

    boolean toggleUpvoteComment(HelpComment comment, Jwt jwt);

    boolean toggleDownvoteComment(HelpComment comment, Jwt jwt);
}

// Yorum güncelle
//    HelpComment updateComment(String commentId, HelpCommentRequest comment);