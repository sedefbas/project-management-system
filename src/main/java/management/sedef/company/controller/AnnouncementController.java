package management.sedef.company.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.company.model.Announcement;
import management.sedef.company.model.mapper.announcementMapper.AnnouncementToResponseMapper;
import management.sedef.company.model.request.AnnouncementRequest;
import management.sedef.company.model.request.AnnouncementUpdateRequest;
import management.sedef.company.model.response.AnnouncementResponse;
import management.sedef.company.service.AnnouncementService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService service;
    private final AnnouncementToResponseMapper announcementToResponseMapper;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyAuthority('announcement:create')")
    public SuccessResponse<Void> createAnnouncement(
            @RequestPart("request") @Valid AnnouncementRequest request,
            @RequestPart("photo") MultipartFile photo) {

        service.saveAnnouncement(request,photo);
        return SuccessResponse.success();
    }


    @GetMapping("/company/{companyId}")
    @PreAuthorize("hasAnyAuthority('announcement:detail')")
    public SuccessResponse<List<AnnouncementResponse>> getAnnouncementsByCompanyId(
            @PathVariable Long companyId) {

        List<Announcement> announcements = service.getAnnouncementsByCompanyId(companyId);

        List<AnnouncementResponse> response = announcements.stream()
                .map(AnnouncementResponse::new)
                .collect(Collectors.toList());

        return SuccessResponse.success(response);
    }

    @GetMapping("/company/{companyId}/active")
    @PreAuthorize("hasAnyAuthority('announcement:detail')")
    public SuccessResponse<List<AnnouncementResponse>> getActiveAnnouncements(
            @PathVariable Long companyId,
            @RequestParam Boolean isActive) {

        List<Announcement> announcements = service.getActiveAndValidAnnouncements(companyId, isActive);

        List<AnnouncementResponse> response = announcements.stream()
                .map(announcementToResponseMapper::map)
                .collect(Collectors.toList());
        return SuccessResponse.success(response);
    }


    @DeleteMapping("/{announcementId}")
    @PreAuthorize("hasAnyAuthority('announcement:delete')")
    public SuccessResponse<Void> deleteAnnouncement(
            @PathVariable Long announcementId) {

        service.deleteAnnouncement(announcementId);
        return SuccessResponse.success();
    }

    @PutMapping("/{announcementId}")
    @PreAuthorize("hasAnyAuthority('announcement:update')")
    public SuccessResponse<Void> updateAnnouncement(
            @RequestBody @Valid AnnouncementUpdateRequest request,
            @PathVariable Long announcementId)  {

        service.update(request,announcementId);
        return SuccessResponse.success();
    }
}
