package management.sedef.Label.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LabelResponse {
    private Long id;
    private String name;
    private String photo;
    private boolean isDefault;
}
