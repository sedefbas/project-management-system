package management.sedef.company.service;

import management.sedef.company.model.Announcement;
import management.sedef.company.model.request.AnnouncementRequest;
import management.sedef.company.model.request.AnnouncementUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnnouncementService {

    Announcement getAnnouncementById(Long announcementId);

    List<Announcement> getAnnouncementsByCompanyId(Long companyId);

    List<Announcement> getActiveAndValidAnnouncements(Long companyId, Boolean isActive);

    List<Announcement> getAnnouncementsByCompanyIdAndTitle(Long companyId, String title);

    Announcement saveAnnouncement(AnnouncementRequest request, MultipartFile photo);

    Announcement deleteAnnouncement(Long announcementId);

    void update(AnnouncementUpdateRequest request, Long announcementId );
}
