package management.sedef.stage.model;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Stage {

    private Long id;

    private String name;

    private String context;

    private Boolean isDefault;
}
