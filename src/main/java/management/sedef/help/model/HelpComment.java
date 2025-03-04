package management.sedef.help.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelpComment {

    private String id;

    private String helpId;

    private HelpUser commenter;

    private String content;

    private Integer upvotes = 0;

    private Integer downvotes = 0;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Set<HelpUser> votedUsers = new HashSet<>();


    public boolean toggleUpvote(HelpUser user) {
        Integer userId = user.getUserId();

        // Kullanıcının daha önce oy verip vermediğini kontrol et
        Optional<HelpUser> existingUser = votedUsers.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst();

        if (existingUser.isPresent()) {
            // Eğer kullanıcı daha önce upvote yapmışsa, upvote'u geri al
            upvotes--;
            votedUsers.remove(existingUser.get());
            return false;  // Upvote geri alındı
        }

        // Eğer kullanıcı daha önce oy vermemişse, upvote yap
        upvotes++;
        votedUsers.add(user);
        return true;  // Upvote yapıldı
    }

    public boolean toggleDownvote(HelpUser user) {
        Integer userId = user.getUserId();

        // Kullanıcının daha önce oy verip vermediğini kontrol et
        Optional<HelpUser> existingUser = votedUsers.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst();

        if (existingUser.isPresent()) {
            // Eğer kullanıcı daha önce downvote yapmışsa, downvote'u geri al
            downvotes--;
            votedUsers.remove(existingUser.get());
            return false;  // Downvote geri alındı
        }

        // Eğer kullanıcı daha önce oy vermemişse, downvote yap
        downvotes++;
        votedUsers.add(user);
        return true;  // Downvote yapıldı
    }

}