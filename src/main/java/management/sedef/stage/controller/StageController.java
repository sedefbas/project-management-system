package management.sedef.stage.controller;


import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.stage.model.Stage;
import management.sedef.stage.service.StageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/stages")
@RequiredArgsConstructor
public class StageController {

    private final StageService service;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('label:detail')")
    public SuccessResponse<List<Stage>> findAll() {
        List<Stage> stages = service.findAll();
        return SuccessResponse.success(stages);
    }
}
