package live.munjeong.server.app.file;

import live.munjeong.server.app.cons.Constant;
import live.munjeong.server.app.domain.File;
import live.munjeong.server.config.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
@Import(TestConfig.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class FileRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private FileRepository fileRepository;

    @Test
    @DisplayName("파일 저장 테스트")
    void fileSaveTest() {
        //given
        UploadFile uploadFile = new UploadFile("test1.jpg",1000L);
        File file = new File(uploadFile);

        //when
        File save = fileRepository.save(file);

        //then
        Assertions.assertThat(file).isSameAs(save);
    }

    @Test
    @DisplayName("파일 저장 시 createDate, lastModifiedDate 테스트")
    void fileSaveDateTest() {
        //given
        UploadFile uploadFile = new UploadFile("test2.jpg",1000L);
        File file = new File(uploadFile);
        LocalDateTime before = LocalDateTime.now();

        //when
        fileRepository.save(file);
        LocalDateTime createdDate = file.getCreatedDate();
        LocalDateTime lastModifiedDate = file.getLastModifiedDate();

        LocalDateTime after = LocalDateTime.now();

        //then
        // 저장시간, 수정시간이 before 이후이고 after 이전이어야한다.
        Assertions.assertThat(before.compareTo(createdDate)).isEqualTo(-1);
        Assertions.assertThat(after.compareTo(createdDate)).isEqualTo(1);
        Assertions.assertThat(before.compareTo(lastModifiedDate)).isEqualTo(-1);
        Assertions.assertThat(after.compareTo(lastModifiedDate)).isEqualTo(1);
    }

    @Test
    @DisplayName("파일 저장 시 createBy, updateBy 테스트 session이 없으면 SYSTEM 이 되어야 한다.")
    void fileSaveCreateByTest() {
        //given
        UploadFile uploadFile = new UploadFile("test3.jpg",1000L);
        File file = new File(uploadFile);

        //when
        fileRepository.save(file);

        //then
        Assertions.assertThat(file.getCreateBy()).isEqualTo(Constant.SYSTEM);
    }
}