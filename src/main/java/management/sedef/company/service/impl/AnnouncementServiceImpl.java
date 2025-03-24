package management.sedef.company.service.impl;


import lombok.RequiredArgsConstructor;
import management.sedef.company.model.Announcement;
import management.sedef.company.model.mapper.announcementMapper.AnnouncementRequestToDomainMapper;
import management.sedef.company.model.request.AnnouncementRequest;
import management.sedef.company.model.request.AnnouncementUpdateRequest;
import management.sedef.company.port.announcementPort.AnnouncementDeletePort;
import management.sedef.company.port.announcementPort.AnnouncementReadPort;
import management.sedef.company.port.announcementPort.AnnouncementSavePort;
import management.sedef.company.service.AnnouncementService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementSavePort savePort;
    private final AnnouncementDeletePort deletePort;
    private final AnnouncementReadPort readPort;
    private final AnnouncementRequestToDomainMapper announcementRequestToDomainMapper ;

    @Override
    public Announcement getAnnouncementById(Long announcementId) {
        return readPort.findById(announcementId);
    }

    @Override
    public List<Announcement> getAnnouncementsByCompanyId(Long companyId) {
        return readPort.findbyCompanyId(companyId);
    }

    @Override
    public List<Announcement> getActiveAndValidAnnouncements(Long companyId, Boolean isActive) {
        return readPort.findByCompanyIdAndIsActiveAndValidUntilAfter(companyId, isActive, Instant.now());
    }

    @Override
    public List<Announcement> getAnnouncementsByCompanyIdAndTitle(Long companyId, String title) {

        return readPort.findByCompanyIdAndTitleContaining(companyId, title);
    }

    @Override
    public Announcement saveAnnouncement(AnnouncementRequest request, MultipartFile photo) {
        Announcement announcementToSave = announcementRequestToDomainMapper.map(request);

        try {
            byte[] photoBytes = photo.getBytes();
            announcementToSave.setPhoto(photoBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        announcementToSave.setCreatedAt(Instant.now());
        return savePort.save(announcementToSave);
    }

    @Override
    public Announcement deleteAnnouncement(Long announcementId) {
        return deletePort.delete(  readPort.findById(announcementId));
    }

    @Override
    public void update(AnnouncementUpdateRequest request, Long  announcementId) {
        Announcement existingAnnouncement = readPort.findById(announcementId);

        Announcement updatedAnnouncement = existingAnnouncement.builder()
                .title(request.getTitle() != null ? request.getTitle() : existingAnnouncement.getTitle())
                .content(request.getContent() != null ? request.getContent() : existingAnnouncement.getContent())
                .photo(request.getPhoto() != null ? request.getPhoto() : existingAnnouncement.getPhoto())
                .validUntil(request.getValidUntil() != null ? request.getValidUntil() : existingAnnouncement.getValidUntil())
                .isActive(request.getIsActive() != null ? request.getIsActive() : existingAnnouncement.getIsActive())
                .updatedAt(Instant.now())
                .build();

        savePort.save(updatedAnnouncement);
    }
}
