package management.sedef.project.service;

import management.sedef.project.model.request.ProjectRequest;

public interface ProjectService {

    void create(ProjectRequest request, Long companyId);


}
