package management.sedef.help.repository;

import management.sedef.help.model.Help;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HelpRepository extends MongoRepository<Help, String> {

    List<Help> findByProjectId(Integer projectId);
}