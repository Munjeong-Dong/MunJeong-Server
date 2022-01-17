package live.munjeong.server.app.file;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class FileStoreTest {
    @Mock
    private MultipartFile multipartFile;

    private FileStore fileStore = new FileStore("E:/munjeong/");

    @Test
    @DisplayName("파일 저장소 파일 저장 테스트")
    void storeFileTest() throws IOException {

        //given
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");
        when(multipartFile.getSize()).thenReturn(1000L);
        doNothing().when(multipartFile).transferTo((java.io.File) any());

        //when
        File file = fileStore.storeFile(multipartFile);

        //then
        Assertions.assertThat(file.getOriginNm()).isEqualTo("test.jpg");
        Assertions.assertThat(file.getExtensions()).isEqualTo("jpg");
        Assertions.assertThat(file.getSize()).isEqualTo(1000L);
        Assertions.assertThat(file.getFileType()).isEqualTo(FileType.IMAGE);
        Assertions.assertThat(file.getStoragePath()).isEqualTo(FileType.IMAGE + LocalDate.now().format(DateTimeFormatter.ofPattern("/yyyy/MM/dd/")));
    }
}