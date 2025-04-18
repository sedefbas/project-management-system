package management.sedef.label.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.label.exception.CompanyLabelNotFound;
import management.sedef.label.model.CompanyLabel;
import management.sedef.label.model.entity.CompanyLabelEntity;
import management.sedef.label.model.mapper.companyLabel.CompanyLabelEntityToDomainMapper;
import management.sedef.label.model.mapper.companyLabel.CompanyLabelToEntityMapper;
import management.sedef.label.port.CompanyLabelPort.CompanyLabelDeletePort;
import management.sedef.label.port.CompanyLabelPort.CompanyLabelReadPort;
import management.sedef.label.port.CompanyLabelPort.CompanyLabelSavePort;
import management.sedef.label.repository.CompanyLabelRepository;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CompanyLabelAdapter implements CompanyLabelDeletePort, CompanyLabelSavePort, CompanyLabelReadPort {

    private final CompanyLabelRepository companyLabelRepository;
    private final CompanyLabelEntityToDomainMapper companyLabelEntityToDomainMapper = CompanyLabelEntityToDomainMapper.initialize();
    private final CompanyLabelToEntityMapper companyLabelToEntityMapper = CompanyLabelToEntityMapper.initialize();


    @Override
    public CompanyLabel delete(CompanyLabel companyLabel) {

        CompanyLabelEntity companyLabelEntity = companyLabelToEntityMapper.map(companyLabel);
        companyLabelRepository.delete(companyLabelEntity);
        return companyLabel;
    }

    @Override
    public CompanyLabel save(CompanyLabel companyLabel) {
        CompanyLabelEntity companyLabelEntity = companyLabelToEntityMapper.map(companyLabel);
        companyLabelRepository.save(companyLabelEntity);
        return companyLabel;
    }

    @Override
    public CompanyLabel findByCompanyIdAndLabelId(Long companyId, Long labelId) {
        CompanyLabelEntity companyLabelEntity = companyLabelRepository.findByCompanyIdAndLabelId(companyId,labelId)
                .orElseThrow(() -> new CompanyLabelNotFound("Company label not found for companyId: " + companyId + " and labelId: " + labelId));
        return companyLabelEntityToDomainMapper.map(companyLabelEntity);
    }
}
