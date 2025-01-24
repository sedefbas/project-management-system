package management.sedef.user.service;

import management.sedef.user.model.User;
import management.sedef.user.model.request.UserUpdateRequest;

public interface UserService {
    User update(UserUpdateRequest updateRequest) ;
}
