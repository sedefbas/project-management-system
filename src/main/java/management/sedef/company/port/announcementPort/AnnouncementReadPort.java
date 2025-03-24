package management.sedef.company.port.announcementPort;


import management.sedef.company.model.Announcement;

import java.time.Instant;
import java.util.List;

public interface AnnouncementReadPort {

    Announcement findById(Long announcementId);

    List<Announcement> findbyCompanyId(Long companyId);

    List<Announcement> findByCompanyIdAndIsActiveAndValidUntilAfter(Long companyId, Boolean isActive, Instant currentDate);

    List<Announcement> findByCompanyIdAndTitleContaining(Long companyId, String title);
}
