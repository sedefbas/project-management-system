package management.sedef.company.model.response;

import lombok.*;



@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupProjectResponse {
    private Long id;
    private String name;
    private String color;
}
