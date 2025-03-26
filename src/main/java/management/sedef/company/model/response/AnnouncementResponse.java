package management.sedef.company.model.response;

import lombok.*;
import management.sedef.company.model.Announcement;

import java.time.Instant;
import java.util.Base64;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementResponse {

    private Long id;
    private Long companyId;
    private String title;
    private String content;
    private String photo;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant validUntil;
    private Boolean isActive;


}