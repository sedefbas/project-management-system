package management.sedef.help.service;

import management.sedef.help.model.Help;
import management.sedef.help.model.HelpComment;
import management.sedef.help.repository.HelpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import java.util.List;

public interface HelpService {
    
    // Belirtilen proje ID'ye göre helpleri getir
    List<Help> getHelpsByProjectId(Integer projectId);

    // Yeni bir help oluştur
    Help createHelp(Help help);

    // Belirtilen help ID'sine göre veriyi getir
    Help getHelpById(String helpId);

    // Help'e yeni bir yorum ekle
    Help addCommentToHelp(String helpId, HelpComment comment);

    // Belirtilen help ID'yi sil
    void deleteHelp(String helpId);
}
