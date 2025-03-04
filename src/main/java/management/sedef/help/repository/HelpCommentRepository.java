package management.sedef.help.repository;

import management.sedef.help.model.document.HelpCommentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HelpCommentRepository extends MongoRepository<HelpCommentDocument,String> {

    List<HelpCommentDocument> findByHelpId(String helpId);

}
