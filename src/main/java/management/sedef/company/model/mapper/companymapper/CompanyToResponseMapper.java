package management.sedef.company.model.mapper.companymapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Company;
import management.sedef.company.model.response.CompanyResponse;
import management.sedef.project.model.Project;
import management.sedef.project.model.request.ProjectRequest;
import management.sedef.user.model.User;
import management.sedef.user.port.UserReadPort;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CompanyToResponseMapper  implements BaseMapper<ProjectRequest, Project> {

    @Autowired
    protected UserReadPort userReadPort;

    @Mapping(target = "owners_id", expression = "java(mapOwnersToIds(company.getOwners()))")
    public abstract CompanyResponse map(Company company);

    protected List<Long> mapOwnersToIds(List<User> owners) {
        if (owners == null || owners.isEmpty()) {
            return Collections.emptyList();
        }
        return owners.stream()
                .map(user -> userReadPort.findById(user.getId())  // User ID'yi al
                        .map(User::getId)
                        .orElse(null))
                .collect(Collectors.toList());
    }
}
