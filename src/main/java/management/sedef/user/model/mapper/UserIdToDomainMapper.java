package management.sedef.user.model.mapper;



import management.sedef.issue.model.Issue;
import management.sedef.issue.model.mapper.issue.IssueEntityToDomainMapper;
import management.sedef.user.model.User;
import management.sedef.user.repository.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserIdToDomainMapper {

        @Autowired
        private UserRepository repository;

        private final UserEntityToUserMapper userMapper = UserEntityToUserMapper.initialize();

        public User toDomain(Long id) {
                if (id == null) return null;

                return repository.findById(id)
                        .map(userMapper::map)  // Veritabanından çekilen entity'yi domain nesnesine çeviriyoruz
                        .orElseThrow(() -> new IllegalStateException("user not found with ID: " + id));
        }

}
