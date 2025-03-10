package management.sedef.notification.controller;

import lombok.RequiredArgsConstructor;
import management.sedef.notification.model.Notification;
import management.sedef.notification.model.request.NotificationRequest;
import management.sedef.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('project:detail')")
    public ResponseEntity<Notification> saveNotification(@RequestBody NotificationRequest request) {
        Notification savedNotification = notificationService.saveNotification(request);
        return ResponseEntity.ok(savedNotification);
    }
}
