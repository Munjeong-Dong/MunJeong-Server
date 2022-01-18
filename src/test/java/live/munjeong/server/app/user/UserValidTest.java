package live.munjeong.server.app.user;

import live.munjeong.server.app.file.valid.FileExtensionValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.Column;
import javax.validation.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserValidTest {

    /**
     * 유저 validation 테스트
     * phone : reg(d{3}-d{3,4}-d{4}$)
     * email : @Email
     * birthday : @Past
     */
    @Test
    @DisplayName("유저 validation 성공 테스트")
    void userValidSuccessTest() {
        // given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        user.setBirthday(LocalDate.of(1989,3,27));
        user.setEmail("test@gmail.com");
        user.setPhone("010-111-1111");

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // then
        Assertions.assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("유저 validation 이메일 실패 테스트")
    void userEmailValidTest() {
        // given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User();
        user.setBirthday(LocalDate.of(1989,3,27));
        user.setEmail("testgmail.com");
        user.setPhone("010-111-1111");

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> violation : violations) {
            Assertions.assertThat(violation.getMessage()).isEqualTo("올바른 형식의 이메일 주소여야 합니다");
        }

        // then
        Assertions.assertThat(violations.size()).isEqualTo(1);
    }
}