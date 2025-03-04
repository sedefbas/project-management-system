package management.sedef.help.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import management.sedef.help.model.HelpUser;
import management.sedef.help.model.enums.HelpStatus;
import management.sedef.help.model.enums.Language;
import management.sedef.help.model.enums.PriorityHelp;
import management.sedef.help.model.enums.Tag;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelpRequest {

    @NotNull
    private HelpUser user;

    @NotNull
    private Integer projectId;

    @NotNull
    private String title;

    @NotNull
    private String description;

    private PriorityHelp priority;

    @NotNull
    private HelpStatus helpstatus;  // OPEN, IN_PROGRESS, RESOLVED, CLOSED

    private Language language;  // Java, Python gibi programlama dili

    private LocalDateTime updatedAt;

    private List<String> screenshotUrls;  // Hata ekran

    private List<String> logFileUrls;  // Hata dosyaları

    private String codeSnippet;  // Hata ile ilgili kod paylaşımı

    private List<HelpUser> mentions;  // @mention desteklemek için

    private List<Tag> tags;  // Etiketler (backend, frontend vs.)
}
