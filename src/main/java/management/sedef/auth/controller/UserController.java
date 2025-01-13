package management.sedef.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.model.Token;
import management.sedef.auth.model.request.LoginRequest;
import management.sedef.auth.model.request.RegisterRequest;
import management.sedef.auth.model.response.TokenResponse;
import management.sedef.auth.service.RegistrationService;
import management.sedef.common.model.entity.response.SuccessResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sedef")
@RequiredArgsConstructor
public class UserController     {



    @GetMapping("/admin/message")
    @PreAuthorize("hasAnyAuthority('READ')")
    public String adminOnlyMessage() {
        return "Bu mesaj Readcılar içindir!";
    }

    @GetMapping("/public")// Yalnızca ADMIN rolüne sahip kullanıcılar erişebilir
    public String adminOnlyMessag() {
        return "Bu mesaj sadece Admin kullanıcılar içindir!";
    }



}
