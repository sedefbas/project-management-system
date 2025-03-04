package management.sedef.company.model;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Builder
public class Group {

    private Long id;
    private String name;
    private String color;
    private Company company;
}
