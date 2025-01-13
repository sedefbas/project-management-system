package management.sedef.company.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import management.sedef.company.model.entity.CompanyEntity;
import management.sedef.user.model.entity.UserEntity;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
public class CompanyUser {

    private Long id;

    private CompanyEntity company;

    private UserEntity user;

    private String role;

    private LocalDate startDate;
}
