package management.sedef.help.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.help.model.Help;
import management.sedef.help.model.HelpComment;
import management.sedef.help.service.HelpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Help>> getHelpsByProjectId(@PathVariable Integer projectId) {
        List<Help> helps = helpService.getHelpsByProjectId(projectId);
        return ResponseEntity.ok(helps);
    }

    @PreAuthorize("hasAuthority('help:detail')")
    @GetMapping("/{id}")
    public ResponseEntity<Help> getHelpById(@PathVariable String id) {
        Help help = helpService.getHelpById(id);
        return ResponseEntity.ok(help);
    }

    @PreAuthorize("hasAuthority('help:create')")
    @PostMapping
    public ResponseEntity<Help> createHelp(@RequestBody @Valid Help help) {
        Help createdHelp = helpService.createHelp(help);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHelp);
    }

    @PreAuthorize("hasAuthority('help:update')")
    @PostMapping("/{helpId}/comments")
    public ResponseEntity<Help> addCommentToHelp(
            @PathVariable String helpId,
            @RequestBody @Valid HelpComment comment) {
        Help updatedHelp = helpService.addCommentToHelp(helpId, comment);
        return ResponseEntity.ok(updatedHelp);
    }

    @PreAuthorize("hasAuthority('help:delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHelp(@PathVariable String id) {
        helpService.deleteHelp(id);
        return ResponseEntity.noContent().build();
    }
}