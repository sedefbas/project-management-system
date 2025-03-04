package management.sedef.company.model.response;


import lombok.*;


@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {
    private Long id;
    private String name;
    private String color;
}
