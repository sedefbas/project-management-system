package management.sedef.common.model.entity;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Column(name = "created_by")
    protected String createdBy;

    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @Column(name = "updated_by")
    protected String updatedBy;

    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    @Column(name = "finishedAt")
    protected LocalDateTime finishedAt;

    @Column(name = "realFinishedAt")
    protected LocalDateTime realFinishedAt;
}
