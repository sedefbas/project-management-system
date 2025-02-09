package management.sedef.Label.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Label {

    private Long id;

    private String name;

    private String photo;

    private Boolean isDefault;

}

