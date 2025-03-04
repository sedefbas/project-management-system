package management.sedef.help.service.ımpl;

import lombok.RequiredArgsConstructor;
import management.sedef.help.model.HelpComment;
import management.sedef.help.model.HelpUser;
import management.sedef.help.model.mapper.HelpCommentRequestToDomainMapper;
import management.sedef.help.model.request.HelpCommentRequest;
import management.sedef.help.port.HelpCommentDeletePort;
import management.sedef.help.port.HelpCommentReadPort;
import management.sedef.help.port.HelpCommentSavePort;
import management.sedef.help.service.HelpCommentService;
import management.sedef.user.model.User;
import management.sedef.user.port.UserReadPort;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HelpCommentServiceImpl implements HelpCommentService {

    private final HelpCommentReadPort readPort;
    private final HelpCommentSavePort savePort;
    private final HelpCommentDeletePort deletePort;
    private final UserReadPort userReadPort;
    private final HelpCommentRequestToDomainMapper helpCommentRequestToDomainMapper = HelpCommentRequestToDomainMapper.initialize();

    @Override
    public Optional<HelpComment> getById(String id) {
        return readPort.findById(id);
    }

    @Override
    public List<HelpComment> getCommentsByHelpId(String helpId) {
        return readPort.getCommentsByHelpId(helpId);
    }

    @Override
    public HelpComment addCommentToHelp(HelpCommentRequest commentRequest) {
        HelpComment comment = helpCommentRequestToDomainMapper.map(commentRequest);
        comment.setCreatedAt(LocalDateTime.now());
        return savePort.save(comment);
    }

    @Override
    public void deleteComment(String commentId) {
        deletePort.deleteById(commentId);
    }

    @Override
    public boolean toggleUpvoteComment(HelpComment comment, Jwt jwt) {
        Long userId = jwt.getClaim("sub");  // JWT'den kullanıcı ID'sini al
        Optional<User> user = userReadPort.findById(userId);  // Kullanıcıyı veritabanından al

        if (user.isPresent()) {
            // Kullanıcıyı HelpUser olarak oluşturuyoruz
            HelpUser helpUser = HelpUser.builder()
                    .userId(userId.intValue())  // Long'dan Integer'a dönüşüm yapılır
                    .firstName(user.get().getFirstName())
                    .lastName(user.get().getLastName())
                    .build();

            // Toggle işlemi: kullanıcı oyu verdiyse geri al, vermediyse ver
            return comment.toggleUpvote(helpUser);
        } else {
            return false;  // Kullanıcı bulunamazsa, işlem yapılmaz
        }
    }

    @Override
    public boolean toggleDownvoteComment(HelpComment comment, Jwt jwt) {
        Long userId = jwt.getClaim("sub");  // JWT'den kullanıcı ID'sini al
        Optional<User> user = userReadPort.findById(userId);  // Kullanıcıyı veritabanından al

        if (user.isPresent()) {
            // Kullanıcıyı HelpUser olarak oluşturuyoruz
            HelpUser helpUser = HelpUser.builder()
                    .userId(userId.intValue())  // Long'dan Integer'a dönüşüm yapılır
                    .firstName(user.get().getFirstName())
                    .lastName(user.get().getLastName())
                    .build();

            // Toggle işlemi: kullanıcı oyu verdiyse geri al, vermediyse ver
            return comment.toggleDownvote(helpUser);
        } else {
            return false;  // Kullanıcı bulunamazsa, işlem yapılmaz
        }
    }

}
