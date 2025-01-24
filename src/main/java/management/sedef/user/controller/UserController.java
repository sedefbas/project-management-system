package management.sedef.user.controller;

import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.user.model.User;
import management.sedef.user.model.request.UserUpdateRequest;
import management.sedef.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('user:update')")
    public SuccessResponse<Void> update(@RequestBody UserUpdateRequest updateRequest) {
            User updatedUser = userService.update(updateRequest);
            return SuccessResponse.success();
    }

}
