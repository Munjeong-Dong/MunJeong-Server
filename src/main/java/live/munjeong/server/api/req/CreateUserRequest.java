package live.munjeong.server.api.req;

import live.munjeong.server.app.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateUserRequest {
    // 전화번호
    @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}$")
    private String phone;
    // 이메일
    @NotBlank
    @Email
    private String email;
    // 이름
    private String nm;
    // 생일
    @Past
    private LocalDate birthday;
    // 비밀번호
    private String pw;

    public User createUser() {
        return new User(phone, email, nm, birthday, pw);
    }
}
