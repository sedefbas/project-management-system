package management.sedef.company.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.user.exception.UserNotFoundException;
import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.entity.CompanyUserEntity;
import management.sedef.company.model.mapper.companyusermapper.CompanyUserEntityToDomainMapper;
import management.sedef.company.model.mapper.companyusermapper.CompanyUserToEntityMapper;
import management.sedef.company.port.companyUserPort.CompanyUserDeletePort;
import management.sedef.company.port.companyUserPort.CompanyUserReadPort;
import management.sedef.company.port.companyUserPort.CompanyUserSavePort;
import management.sedef.company.repository.CompanyUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyUserAdapter implements CompanyUserReadPort, CompanyUserSavePort, CompanyUserDeletePort {

   private final CompanyUserRepository repository;
   private final CompanyUserToEntityMapper companyUserToEntityMapper = CompanyUserToEntityMapper.initialize();
   private final   CompanyUserEntityToDomainMapper companyUserEntityToDomainMapper = CompanyUserEntityToDomainMapper.initialize();

    @Override
    public CompanyUser findByUserId(Long id) {
        return repository.findByUserId(id)
                .map(companyUserEntityToDomainMapper::map)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    @Override
    public List<CompanyUser> findByCompanyId(Long id) {
        return repository.findByCompanyId(id).stream().map(companyUserEntityToDomainMapper::map).toList();
    }

    @Override
    public CompanyUser findByCompanyIdAndUserId(Long companyId, Long userId) {
         CompanyUserEntity companyUserEntity = repository.findByCompanyIdAndUserId(companyId,userId).orElseThrow();
        return companyUserEntityToDomainMapper.map(companyUserEntity);
    }

    @Override
    public CompanyUser save(CompanyUser companyUser) {
        CompanyUserEntity companyUserEntity = companyUserToEntityMapper.map(companyUser);
        repository.save(companyUserEntity);
        return companyUserEntityToDomainMapper.map(companyUserEntity);
    }

    @Override
    public void delete(CompanyUser companyUser) {
        final CompanyUserEntity companyUserEntity = companyUserToEntityMapper.map(companyUser);
        repository.delete(companyUserEntity);
    }
}
