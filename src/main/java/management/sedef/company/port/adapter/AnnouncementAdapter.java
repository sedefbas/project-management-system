package management.sedef.company.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.company.model.Announcement;
import management.sedef.company.model.entity.AnnouncementEntity;
import management.sedef.company.model.mapper.announcementMapper.AnnouncemenEntityToDomainMapper;
import management.sedef.company.model.mapper.announcementMapper.AnnouncementToEntityMapper;
import management.sedef.company.port.announcementPort.AnnouncementDeletePort;
import management.sedef.company.port.announcementPort.AnnouncementReadPort;
import management.sedef.company.port.announcementPort.AnnouncementSavePort;
import management.sedef.company.repository.AnnouncementRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AnnouncementAdapter implements AnnouncementDeletePort, AnnouncementReadPort, AnnouncementSavePort {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncemenEntityToDomainMapper announcemenEntityToDomainMapper = AnnouncemenEntityToDomainMapper.initialize();
    private final AnnouncementToEntityMapper announcementToEntityMapper = AnnouncementToEntityMapper.initialize();

    @Override
    public Announcement delete(Announcement announcement) {
        AnnouncementEntity entity = announcementToEntityMapper.map(announcement);
        announcementRepository.delete(entity);
        return announcemenEntityToDomainMapper.map(entity);
    }

    @Override
    public Announcement findById(Long announcementId) {
        return announcementRepository.findById(announcementId)
                .map(announcemenEntityToDomainMapper::map)
                .orElse(null);
    }

    @Override
    public List<Announcement> findbyCompanyId(Long companyId) {
        return announcementRepository.findByCompanyId(companyId).stream()
                .map(announcemenEntityToDomainMapper::map)  // Entity'yi Domain'e dönüştürme
                .collect(Collectors.toList());
    }


    @Override
    public List<Announcement> findByCompanyIdAndIsActiveAndValidUntilAfter(Long companyId, Boolean isActive, Instant currentDate) {
        return announcementRepository.findByCompanyIdAndIsActiveAndValidUntilAfter(companyId, isActive, currentDate).stream()
                .map(announcemenEntityToDomainMapper::map)  // Entity'yi Domain'e dönüştürme
                .collect(Collectors.toList());
    }


    @Override
    public List<Announcement> findByCompanyIdAndTitleContaining(Long companyId, String title) {
        return announcementRepository.findByCompanyIdAndTitleContaining(companyId, title).stream()
                .map(announcemenEntityToDomainMapper::map) // Burada doğru mapper metodunu kullanıyoruz
                .collect(Collectors.toList());
    }


    @Override
    public Announcement save(Announcement announcement) {
        AnnouncementEntity entity = announcementToEntityMapper.map(announcement); // Domain -> Entity dönüşümü
        AnnouncementEntity savedEntity = announcementRepository.save(entity); // Veritabanına kaydetme
        return announcemenEntityToDomainMapper.map(savedEntity);  // Kaydedilen entity'yi domain modeline dönüştürme
    }
}
