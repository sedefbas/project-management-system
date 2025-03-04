package management.sedef.help.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import management.sedef.help.model.HelpUser;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelpCommentRequest {

    @NotNull
    private String helpId;

    @NotNull
    private HelpUser commenter;

    @NotNull
    private String content;

}
