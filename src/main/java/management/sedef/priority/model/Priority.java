package management.sedef.priority.model;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Priority {
    private Long id;

    private String name;

    private String photo;

    private Boolean isDefault;
}
