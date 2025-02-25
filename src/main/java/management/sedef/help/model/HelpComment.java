package management.sedef.help.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelpComment {

    private String id; // Yorumun MongoDB ObjectId değeri

    private HelpUser user; // Yorumu yapan kişi

    private String message; // Yorum içeriği

    private LocalDateTime createdAt; // Yorumun yapıldığı tarih
}