package management.sedef.project.kafka.event;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectUserEvent {
   private Long recipientUserId;
   private Long projeId;
   private Long taskAssignerId;
   private Date assignmentDate;
}
