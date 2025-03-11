package management.sedef.label.service.impl;


import lombok.RequiredArgsConstructor;
import management.sedef.label.model.CompanyLabel;
import management.sedef.label.model.Label;
import management.sedef.label.model.mapper.companyLabel.CompanyLabelRequestToLabelMapper;
import management.sedef.label.model.request.CompanyLabelRequest;
import management.sedef.label.port.CompanyLabelPort.CompanyLabelDeletePort;
import management.sedef.label.port.CompanyLabelPort.CompanyLabelReadPort;
import management.sedef.label.port.CompanyLabelPort.CompanyLabelSavePort;
import management.sedef.label.service.CompanylabelService;
import management.sedef.company.model.Company;
import management.sedef.company.service.CompanyService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CompanyLabelServiceImpl implements CompanylabelService {

    private final CompanyLabelReadPort readPort;
    private final CompanyLabelDeletePort deletePort;
    private final CompanyLabelSavePort savePort;
    private final CompanyLabelRequestToLabelMapper companyLabelRequestToLabelMapper = CompanyLabelRequestToLabelMapper.initialize();
    private final CompanyService companyService;

    @Override
    public void addLabelToCompany(Long companyId, CompanyLabelRequest request) {

        Company company = companyService.findCompanyById(companyId);
        Label label = companyLabelRequestToLabelMapper.map(request);
        label.setIsDefault(false);

        CompanyLabel companyLabel = CompanyLabel.builder()
                .company(company)
                .label(label)
                .build();
        savePort.save(companyLabel);
    }

    @Override
    public void removeLabelFromCompany(Long companyId, Long labelId) {
        CompanyLabel companyLabel = readPort.findByCompanyIdAndLabelId(companyId, labelId);
        deletePort.delete(companyLabel);
    }

}
