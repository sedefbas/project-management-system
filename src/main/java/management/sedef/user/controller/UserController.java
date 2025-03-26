package management.sedef.user.controller;

import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.user.model.User;
import management.sedef.user.model.request.UserUpdateRequest;
import management.sedef.user.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyAuthority('user:update')")
    public SuccessResponse<Void> update(
            @RequestPart("updateRequest") UserUpdateRequest updateRequest,
            @RequestPart(value = "photoFile", required = false) MultipartFile photoFile) {

        userService.update(updateRequest, photoFile);
        return SuccessResponse.success();
    }

}
