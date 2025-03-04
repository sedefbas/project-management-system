package management.sedef.help.model;


import lombok.*;
import management.sedef.help.model.enums.HelpStatus;
import management.sedef.help.model.enums.Language;
import management.sedef.help.model.enums.PriorityHelp;
import management.sedef.help.model.enums.Tag;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Help {

    private String id;

    private HelpUser user;

    private Integer projectId;

    private String title;

    private String description;

    private PriorityHelp priority;

    private HelpStatus helpstatus;  // OPEN, IN_PROGRESS, RESOLVED, CLOSED

    private Language language;  // Java, Python gibi programlama dili

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<String> screenshotUrls;  // Hata ekran

    private List<String> logFileUrls;  // Hata dosyaları

    private String codeSnippet;  // Hata ile ilgili kod paylaşımı

    private List<HelpUser> mentions;  // @mention desteklemek için

    private List<Tag> tags;  // Etiketler (backend, frontend vs.)
}
