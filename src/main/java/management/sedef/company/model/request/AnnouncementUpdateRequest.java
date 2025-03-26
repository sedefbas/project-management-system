package management.sedef.company.model.request;


import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementUpdateRequest {

    private Long companyId;

    private String title;

    private String content;

    private Instant validUntil;

    private Boolean isActive = true;

}
