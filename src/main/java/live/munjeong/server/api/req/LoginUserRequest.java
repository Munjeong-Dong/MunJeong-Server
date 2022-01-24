package live.munjeong.server.api.req;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class LoginUserRequest {
    // 이메일
    @NotBlank
    @Email
    private String email;
    // 비밀번호
    private String pw;
    // 자동 로그인 여부
    private boolean autoLogin;
}
