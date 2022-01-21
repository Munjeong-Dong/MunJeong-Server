package live.munjeong.server.app.user;

import live.munjeong.server.app.domain.User;
import live.munjeong.server.app.user.request.CreateUserReq;
import live.munjeong.server.app.user.search.UserSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long saveUser(CreateUserReq userReq) {
        log.debug("saveUser userReq [{}]", userReq);
        User user = userRepository.save(userReq.createUser());
        return user.getId();
    }

    public void deleteUser(Long userId) {
        log.debug("deleteUser userId [{}]", userId);
        userRepository.deleteById(userId);
    }

    public Page<User> findUsers(UserSearch userSearch, PageRequest pageRequest) {
        if(StringUtils.hasText(userSearch.getSearchNm())) {
            return userRepository.findAllByNmContaining(userSearch.getSearchNm(), pageRequest);
        }
        return userRepository.findAll(pageRequest);
    }
}
