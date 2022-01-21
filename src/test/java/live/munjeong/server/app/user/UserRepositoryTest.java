package live.munjeong.server.app.user;

import live.munjeong.server.app.domain.User;
import live.munjeong.server.config.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.*;

@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
@Import(TestConfig.class)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("사용자 저장 테스트")
    void fileSaveTest() {
        //given
        User user = new User("010-1111-2222","test@Email.com","테스트유저1", LocalDate.of(1989,3,27),"pw");

        //when
        User save = userRepository.save(user);

        //then
        Assertions.assertThat(user).isSameAs(save);
        Assertions.assertThat(user).isEqualTo(save);
    }

    @Test
    @DisplayName("사용자 찾기 테스트")
    void userSearchTest() {
        initTestData();

        Page<User> result = userRepository.findAllByNmContaining("테스트", PageRequest.of(2, 10, Sort.by(Sort.Direction.DESC, "nm")));
        System.out.println("result = " + result);
        Assertions.assertThat(result.getTotalElements()).isEqualTo(100L);
        Assertions.assertThat(result.getTotalPages()).isEqualTo(10);
        Assertions.assertThat(result.getContent().size()).isEqualTo(10);
        Assertions.assertThat(result.getContent().get(0).getNm()).isEqualTo("테스트80");
    }

    private void initTestData() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            users.add(new User("테스트" + i));
            if (i % 2 == 0) {
                users.add(new User("서치" + i));
            }
        }
        userRepository.saveAll(users);
    }
}
