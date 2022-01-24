package live.munjeong.server.app.user;

import live.munjeong.server.app.domain.User;
import live.munjeong.server.app.exception.NonSearchUserException;
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

    public Long saveUser(User user) {
        log.debug("saveUser user [{}]", user);
        return userRepository.save(user).getId();
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

    public User findUser(Long userId) throws NonSearchUserException {
        return userRepository.findById(userId).orElseThrow(NonSearchUserException::new);
    }

    public User loginUser(String email, String pw) throws NonSearchUserException {
        return userRepository.findUserByEmailAndPw(email, pw).orElseThrow(NonSearchUserException::new);
    }
}
