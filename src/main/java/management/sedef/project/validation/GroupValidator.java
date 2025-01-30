package management.sedef.project.validation;

import management.sedef.project.exception.GroupAlreadyExist;

import java.util.List;

public class GroupValidator {

    public static void validateGroupAlreadyAdded(List<Long> currentGroupIds, Long groupId) {
        if (currentGroupIds.contains(groupId)) {
            throw new GroupAlreadyExist("Grup zaten projeye eklenmiş: " + groupId);
        }
    }
}
