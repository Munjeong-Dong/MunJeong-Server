package live.munjeong.server.app.user;

import live.munjeong.server.app.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByNmContaining(String nm, Pageable pageable);

    Optional<User> findUserByEmailAndPw(String email, String pw);
}
