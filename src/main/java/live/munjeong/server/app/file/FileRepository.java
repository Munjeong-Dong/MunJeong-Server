package live.munjeong.server.app.file;

import live.munjeong.server.app.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}
