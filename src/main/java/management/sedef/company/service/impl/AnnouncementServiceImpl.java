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
import management.sedef.minio.payload.BucketNameEnum;
import management.sedef.minio.payload.FileResponse;
import management.sedef.minio.service.MinioService;
import management.sedef.minio.util.FileTypeUtils;
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
    private final MinioService minioService;

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

    public Announcement saveAnnouncement(AnnouncementRequest request, MultipartFile photo) {

        if (photo == null || photo.isEmpty()) {
            throw new IllegalArgumentException("Photo cannot be null or empty");
        }

        String bucketName = BucketNameEnum.ANNOUNCEMENT_PHOTO.getBucketName();
        String fileType = FileTypeUtils.getFileType(photo);

        String photoUrl = null;

        if (fileType != null) {
            FileResponse fileResponse = minioService.putObject(photo, bucketName, fileType);
            photoUrl = minioService.getObjectUrl(bucketName, fileResponse.getFilename());
        }

        Announcement announcementToSave = announcementRequestToDomainMapper.map(request);
        announcementToSave.setPhoto(photoUrl);
        announcementToSave.setCreatedAt(Instant.now());
        return savePort.save(announcementToSave);
    }


    @Override
    public Announcement deleteAnnouncement(Long announcementId) {
        return deletePort.delete(  readPort.findById(announcementId));
    }

    @Override
    public void update(AnnouncementUpdateRequest request, Long  announcementId, MultipartFile photo) {


        String bucketName = BucketNameEnum.ANNOUNCEMENT_PHOTO.getBucketName();

        String fileType = FileTypeUtils.getFileType(photo);
        String photoUrl = null;

        if (fileType != null) {
            FileResponse fileResponse = minioService.putObject(photo, bucketName, fileType);
            photoUrl = minioService.getObjectUrl(bucketName, fileResponse.getFilename());
        }

        Announcement existingAnnouncement = readPort.findById(announcementId);

        Announcement updatedAnnouncement = existingAnnouncement.builder()
                .title(request.getTitle() != null ? request.getTitle() : existingAnnouncement.getTitle())
                .content(request.getContent() != null ? request.getContent() : existingAnnouncement.getContent())
                .photo(photoUrl != null ? photoUrl : existingAnnouncement.getPhoto())
                .validUntil(request.getValidUntil() != null ? request.getValidUntil() : existingAnnouncement.getValidUntil())
                .isActive(request.getIsActive() != null ? request.getIsActive() : existingAnnouncement.getIsActive())
                .updatedAt(Instant.now())
                .build();

        savePort.save(updatedAnnouncement);
    }
}
