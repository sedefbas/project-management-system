package management.sedef.help.controller;

import management.sedef.help.model.Help;
import management.sedef.help.model.HelpComment;
import management.sedef.help.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helps")
public class HelpController {

    private final HelpService helpService;

    public HelpController(HelpService helpService) {
        this.helpService = helpService;
    }

    // 🔹 1. Belirli proje ID'ye göre tüm help'leri getir
    @GetMapping("/project/{projectId}")
    public List<Help> getHelpsByProjectId(@PathVariable Integer projectId) {
        return helpService.getHelpsByProjectId(projectId);
    }

    // 🔹 2. Yeni bir help oluştur
    @PostMapping
    public Help createHelp(@RequestBody Help help) {
        return helpService.createHelp(help);
    }

    // 🔹 3. Belirli help ID'ye göre veriyi getir
    @GetMapping("/{helpId}")
    public Help getHelpById(@PathVariable String helpId) {
        return helpService.getHelpById(helpId);
    }

    // 🔹 4. Belirli bir help'e yorum ekle
    @PostMapping("/{helpId}/comments")
    public Help addCommentToHelp(@PathVariable String helpId, @RequestBody HelpComment comment) {
        return helpService.addCommentToHelp(helpId, comment);
    }

    // 🔹 5. Belirli help ID'yi sil
    @DeleteMapping("/{helpId}")
    public void deleteHelp(@PathVariable String helpId) {
        helpService.deleteHelp(helpId);
    }
}