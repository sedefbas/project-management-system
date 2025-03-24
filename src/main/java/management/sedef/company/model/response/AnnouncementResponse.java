package management.sedef.company.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.company.model.Announcement;

import java.time.Instant;
import java.util.Base64;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AnnouncementResponse {

    private Long id;
    private Long companyId;
    private String title;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant validUntil;
    private Boolean isActive;

    private String photoBase64;

    public AnnouncementResponse(Announcement announcement) {
        this.id = announcement.getId();
        this.companyId = announcement.getCompany().getId();
        this.title = announcement.getTitle();
        this.content = announcement.getContent();
        this.createdAt = announcement.getCreatedAt();
        this.updatedAt = announcement.getUpdatedAt();
        this.validUntil = announcement.getValidUntil();
        this.isActive = announcement.getIsActive();

        if (announcement.getPhoto() != null) {
            this.photoBase64 = Base64.getEncoder().encodeToString(announcement.getPhoto());
        }
    }
}