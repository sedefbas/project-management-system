package management.sedef.issue.port.adapter;


import lombok.RequiredArgsConstructor;
import management.sedef.issue.exception.IssueLinkNotFoundException;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.IssueLink;
import management.sedef.issue.model.entity.IssueEntity;
import management.sedef.issue.model.entity.IssueLinkEntity;
import management.sedef.issue.model.enums.IssueLinkType;
import management.sedef.issue.model.mapper.issue.IssueToEntityMapper;
import management.sedef.issue.model.mapper.issueLink.IssueLinkEntityToDomainMapper;
import management.sedef.issue.model.mapper.issueLink.IssueLinkToEntityMapper;
import management.sedef.issue.port.issueLinkPort.IssueLinkDeletePort;
import management.sedef.issue.port.issueLinkPort.IssueLinkReadPort;
import management.sedef.issue.port.issueLinkPort.IssueLinkSavePort;
import management.sedef.issue.repository.IssueLinkRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class IssueLinkAdapter implements IssueLinkSavePort, IssueLinkDeletePort, IssueLinkReadPort {

    private final IssueLinkRepository issueLinkRepository;
    private final IssueLinkEntityToDomainMapper issueLinkEntityToDomainMapper = IssueLinkEntityToDomainMapper.initialize();
    private final IssueLinkToEntityMapper issueLinkToEntityMapper = IssueLinkToEntityMapper.initialize();


    @Override
    public IssueLink removeDependency(IssueLink issueLink) {
        IssueLinkEntity issueLinkEntity = issueLinkToEntityMapper.map(issueLink);
        issueLinkRepository.delete(issueLinkEntity);
        return issueLinkEntityToDomainMapper.map(issueLinkEntity);
    }


    @Override
    public List<IssueLink> getDependencies(Long issueId) {
        List<IssueLinkEntity> issueLinkEntities = issueLinkRepository.findByIssueId(issueId);
        return issueLinkEntities.stream()
                .map(issueLinkEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }


    //todo sıkıntılara davetiye çıkartacak bir kod yazdığımın gayet farkındayım null dönmemeli .
    @Override
    public IssueLink findByIssueIdAndLinkedIssueId(Long issueId, Long linkedIssueId) {
        return issueLinkRepository.findByIssueIdAndLinkedIssueId(issueId, linkedIssueId)
                .map(issueLinkEntity -> issueLinkEntityToDomainMapper.map(issueLinkEntity))
                .orElse(null);
    }


    @Override
    public IssueLink findbyId(Long issueLinkId) {
        return issueLinkRepository.findById(issueLinkId)
                .map(issueLinkEntity -> issueLinkEntityToDomainMapper.map(issueLinkEntity))
                .orElseThrow(() -> new IssueLinkNotFoundException("IssueLink not found for ID: " + issueLinkId));
    }


    @Override
    public IssueLink save(IssueLink issueLink) {
        IssueLinkEntity issueLinkEntity = issueLinkToEntityMapper.map(issueLink);
        IssueLinkEntity savedEntity = issueLinkRepository.save(issueLinkEntity);
        return issueLinkEntityToDomainMapper.map(savedEntity);
    }
}
