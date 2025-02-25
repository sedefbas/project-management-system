package management.sedef.help.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelpUser {
    
    private Integer userId; // Kullanıcı ID (MySQL'den gelen ID)

    private String firstName; // Kullanıcı adı

    private String lastName; // Kullanıcı soyadı

    private String photoUrl; // Profil fotoğrafı URL
}