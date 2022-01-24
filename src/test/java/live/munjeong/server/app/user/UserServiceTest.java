package live.munjeong.server.app.user;

import live.munjeong.server.app.domain.User;
import live.munjeong.server.app.user.request.CreateUserReq;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("유저 저장 테스트")
    void saveUserTest() {
        User user = new User("010-1111-2222","test@gmail.com","테스트", LocalDate.of(1989,1,12),"pwTest");
        user.setId(1L);

        //given
        given(userRepository.save(any())).willReturn(user);

        //when
        Long saveUserId = userService.saveUser(user);

        //then
        assertThat(saveUserId).isEqualTo(1L);
    }

}