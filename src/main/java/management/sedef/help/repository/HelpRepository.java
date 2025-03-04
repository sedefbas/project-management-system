package management.sedef.help.repository;

import management.sedef.help.model.document.HelpDocument;

import java.util.List;

import management.sedef.help.model.enums.HelpStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HelpRepository extends MongoRepository<HelpDocument, String> {

    List<HelpDocument> findByProjectId(Integer projectId);
    List<HelpDocument> findByProjectIdAndHelpstatus(Integer projectId, HelpStatus helpStatus);

}