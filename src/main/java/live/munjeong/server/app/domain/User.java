package live.munjeong.server.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import live.munjeong.server.app.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Table(name = "USERS")
@TableGenerator(
        name = "USER_SEQ_GENERATOR",
        table = "TB_SEQUENCES",
        pkColumnValue = "USER_SEQ",
        allocationSize = 1)
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(generator = "USER_SEQ_GENERATOR", strategy = GenerationType.TABLE)
    @Column(name = "user_id", nullable = false)
    private Long id;
    // 전화번호 ex 010-1234-5678
    @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}$")
    private String phone;

    @Email
    @Column(unique = true)
    private String email;
    // 이름
    private String nm;

    // 생일
    @Past
    private LocalDate birthday;
    // 비밀번호
    private String pw;

    /**
     * 생성자 함수
     */
    public User(String nm) {
        this.nm = nm;
    }

    public User(String phone, String email, String nm, LocalDate birthday, String pw) {
        this.phone = phone;
        this.email = email;
        this.nm = nm;
        this.birthday = birthday;
        this.pw = pw;
    }
}
