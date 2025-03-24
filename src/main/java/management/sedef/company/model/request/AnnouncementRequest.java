package management.sedef.company.model.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementRequest {

    private Long companyId;

    private String title;

    private String content;

    private Instant validUntil;

    private Boolean isActive = true;
}
