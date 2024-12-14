package management.sedef.auth.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import management.sedef.common.model.entity.BaseEntity;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class InvalidToken extends BaseEntity {

    private Long id;
    private String tokenId;

}

