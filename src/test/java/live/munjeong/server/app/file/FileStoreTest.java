package live.munjeong.server.app.file;

import live.munjeong.server.config.TestConfig;
import org.apache.commons.io.FilenameUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.io.File.separator;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@Import({TestConfig.class, FileStore.class})
class FileStoreTest {
    @Mock
    private MultipartFile multipartFile;

    private FileStore fileStore = new FileStore("files/");


    @Test
    @DisplayName("파일 경로 가져오기 테스트")
    void getFullPathTest() {
        //given
        File file = new File("test.jpg",100L);
        String toDayPath = LocalDate.now().format(DateTimeFormatter.ofPattern(separator+"yyyy"+separator+"MM"+separator+"dd"));

        //when
        String path = fileStore.getFullPath(file);

        //then
        Assertions.assertThat(path).contains("files", "IMAGE" + toDayPath);
    }
    
    
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
        Assertions.assertThat(file.getStoragePath()).isEqualTo(FileType.IMAGE + LocalDate.now().format(DateTimeFormatter.ofPattern(separator + "yyyy"+separator+"MM"+separator+"dd"+separator)));
    }

    @Test
    @DisplayName("파일 삭제 테스트")
    void deleteTest() throws IOException {
        //given
        File file = new File("test.txt", 10L);
        java.io.File realFile = createTestFile(file);
        MockMultipartFile mockMultipartFile = getMockMultipartFile(realFile);

        fileStore.deleteFile(file);

    }

    private java.io.File createTestFile(File file) throws IOException {
        java.io.File testFile = fileStore.getRealFile(file);
        Path path = testFile.getParentFile().toPath();
        Files.createDirectories(path);
        FileWriter fw = new FileWriter(testFile);
        fw.write("test");
        fw.flush();
        fw.close();

        return testFile;
    }

    private MockMultipartFile getMockMultipartFile(java.io.File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        return new MockMultipartFile(file.getName(), fileInputStream);
    }

}