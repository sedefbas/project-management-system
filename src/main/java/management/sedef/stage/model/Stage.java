package management.sedef.stage.model;


import lombok.*;
import management.sedef.stage.model.enums.StageType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Stage {

    private Long id;

    private StageType name;

    private String context;

    private Boolean isDefault;
}
