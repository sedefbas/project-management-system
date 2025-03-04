package management.sedef.help.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.help.model.Help;
import management.sedef.help.model.enums.HelpStatus;
import management.sedef.help.model.request.HelpRequest;
import management.sedef.help.service.HelpService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helps")
@RequiredArgsConstructor
public class HelpController {

    private final HelpService helpService;


    @PreAuthorize("hasAuthority('help:list')")
    @GetMapping("/project/{projectId}")
    public SuccessResponse<List<Help>> getHelpsByProjectId(@PathVariable Integer projectId) {
        List<Help> helps = helpService.getHelpsByProjectId(projectId);
        return SuccessResponse.success(helps);
    }

    @PreAuthorize("hasAuthority('help:list')")
    @GetMapping("/project/{projectId}/status/{status}")
    public SuccessResponse<List<Help>> getHelpsByProjectIdAndStatus(
            @PathVariable Integer projectId,
            @PathVariable HelpStatus status) {
        List<Help> helps = helpService.getHelpsByProjectIdAndStatus(projectId, status);
        return SuccessResponse.success(helps);
    }


    @PreAuthorize("hasAuthority('help:detail')")
    @GetMapping("/{id}")
    public SuccessResponse<Help> getHelpById(@PathVariable String id) {
        Help help = helpService.getHelpById(id);
        return SuccessResponse.success(help);
    }

    @PreAuthorize("hasAuthority('help:create')")
    @PostMapping
    public SuccessResponse<Help> createHelp(@RequestBody @Valid HelpRequest helpRequest) {
        Help createdHelp = helpService.createHelp(helpRequest);
        return SuccessResponse.success(createdHelp);
    }

    @PreAuthorize("hasAuthority('help:delete')")
    @DeleteMapping("/{id}")
    public SuccessResponse<Void> deleteHelp(@PathVariable String id) {
        helpService.deleteHelp(id);
        return SuccessResponse.success();
    }
}
