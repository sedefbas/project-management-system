package management.sedef.priority.model.request;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyPriorityRequest {
    private String name;
    private String photo;
}
