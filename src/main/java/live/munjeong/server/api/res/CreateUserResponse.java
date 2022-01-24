package live.munjeong.server.api.res;

import live.munjeong.server.app.domain.User;
import lombok.Data;

@Data
public class CreateUserResponse {
    private String email;
    private String pw;
    private String nm;


    public CreateUserResponse(User user) {
        this.email = user.getEmail();
        this.pw = user.getPw();
        this.nm = user.getNm();
    }
}
