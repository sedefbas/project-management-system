package management.sedef.auth.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import management.sedef.company.model.SubGroup;

@Getter
@RequiredArgsConstructor
public enum TokenClaims {

    ALGORITHM("alg"),
    TYPE("typ"),
    USER_ID("userId"),
    USER_FIRST_NAME("userFirstName"),
    USER_LAST_NAME("userLastName"),
    USER_MAIL("userMail"),
    USER_STATUS("userStatus"),
    USER_ROLE("userRole"),
    USER_PERMISSIONS("userPermissions"),

    COMPANY_ID("companyId"),
    PROJECT_ID("projectId"),
    GROUP_ID("groupId"),
    SUB_GROUP_ID("subGroupId");

    private final String value;

}