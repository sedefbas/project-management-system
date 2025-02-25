package management.sedef.help.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "helps") 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Help {

    @Id
    private String id;

    private HelpUser user;

    private String title; 

    private String description; 

    private PriorityHelp priority; 

    private LocalDateTime createdAt; 

    private Integer projectId; 

    private List<HelpComment> comments; 
}