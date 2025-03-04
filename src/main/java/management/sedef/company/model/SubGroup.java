package management.sedef.company.model;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Data
@Builder
public class SubGroup {

    private Long id;
    private String name;
    private String color;
    private Group group;
}
