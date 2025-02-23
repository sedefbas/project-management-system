package management.sedef.issue.model.mapper.issue;

import management.sedef.issue.model.Issue;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import management.sedef.issue.repository.IssueRepository;

@Mapper(componentModel = "spring")
public abstract class IssueIdToDomainMapper {

    @Autowired
    private IssueRepository issueRepository;

    // Mevcut mapper'ı initialize ederek kullanalım
    private final IssueEntityToDomainMapper issueMapper = IssueEntityToDomainMapper.initialize();

    public Issue toDomain(Long id) {
        if (id == null) return null;
        
        return issueRepository.findById(id)
                .map(issueMapper::map)  // Veritabanından çekilen entity'yi domain nesnesine çeviriyoruz
                .orElseThrow(() -> new IllegalStateException("Issue not found with ID: " + id));
    }
}
