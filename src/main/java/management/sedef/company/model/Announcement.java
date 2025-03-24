package management.sedef.company.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@SuperBuilder
public class Announcement {

    private Long id;

    private Company company;

    private String title;

    private String content;

    private byte[] photo;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant validUntil;

    private Boolean isActive = true;
}
