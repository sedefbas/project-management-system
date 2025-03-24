package management.sedef.company.model;


import lombok.*;

@Data
@Builder
public class Group {

    private Long id;
    private String name;
    private String color;
    private Company company;
}
