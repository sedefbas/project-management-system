package management.sedef.user.service;

import management.sedef.user.model.User;
import management.sedef.user.model.request.UserUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User update(UserUpdateRequest updateRequest, MultipartFile photoFile) ;
    User getUserFromToken(String token);
}
