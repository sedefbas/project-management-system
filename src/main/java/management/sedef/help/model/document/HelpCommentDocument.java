package management.sedef.help.model.document;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import management.sedef.help.model.HelpUser;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "help_comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelpCommentDocument {

    @Id
    private String id;

    private String helpId;  // Yoruma ait olduğu Help'in ID'si

    private HelpUser commenter;  // Yorumu yazan kullanıcının ID'si

    private String content; // Yorum içeriği

    private Integer upvotes = 0; // Upvote sayısı

    private Integer downvotes = 0; // Downvote sayısı

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Set<HelpUser> votedUsers = new HashSet<>();

}
