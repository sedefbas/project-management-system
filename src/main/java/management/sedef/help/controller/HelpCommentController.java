package management.sedef.help.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.help.model.HelpComment;
import management.sedef.help.model.request.HelpCommentRequest;
import management.sedef.help.service.HelpCommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/help-comments")
@RequiredArgsConstructor
public class HelpCommentController {

    private final HelpCommentService helpCommentService;

    @PreAuthorize("hasAuthority('help-comment:list')")
    @GetMapping("/help/{helpId}")
    public SuccessResponse<List<HelpComment>> getCommentsByHelpId(@PathVariable String helpId) {
        List<HelpComment> comments = helpCommentService.getCommentsByHelpId(helpId);
        return SuccessResponse.success(comments);
    }

    @PreAuthorize("hasAuthority('help-comment:create')")
    @PostMapping
    public SuccessResponse<HelpComment> addCommentToHelp(@RequestBody @Valid HelpCommentRequest comment) {
        HelpComment createdComment = helpCommentService.addCommentToHelp(comment);
        return SuccessResponse.success(createdComment);
    }

    @PreAuthorize("hasAuthority('help-comment:delete')")
    @DeleteMapping("/{commentId}")
    public SuccessResponse<Void> deleteComment(@PathVariable String commentId) {
        helpCommentService.deleteComment(commentId);
        return SuccessResponse.success();
    }


    @PreAuthorize("hasAuthority('help-comment:upvote')")
    @PostMapping("/{commentId}/upvote")
    public SuccessResponse<Boolean> upvoteComment(@PathVariable String commentId, @AuthenticationPrincipal Jwt jwt) {
        // Yorum ID'sine göre HelpComment'i bulmamız gerekiyor
        Optional<HelpComment> comment = helpCommentService.getById(commentId); // Yorum alınır

        boolean result = helpCommentService.toggleUpvoteComment(comment.get(), jwt);

        return SuccessResponse.success(result);
    }

    @PreAuthorize("hasAuthority('help-comment:downvote')")
    @PostMapping("/{commentId}/downvote")
    public SuccessResponse<Boolean> downvoteComment(@PathVariable String commentId, @AuthenticationPrincipal Jwt jwt) {

        Optional<HelpComment> comment = helpCommentService.getById(commentId); // Yorum alınır

        boolean result = helpCommentService.toggleDownvoteComment(comment.get(), jwt);

        return SuccessResponse.success(result);
    }

}