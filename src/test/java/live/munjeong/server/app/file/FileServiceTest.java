package live.munjeong.server.app.file;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private FileService fileService;

    @Test
    @DisplayName("파일 서비스 upload 테스트")
    void uploadTest() {
        //given
        UploadFile uploadFile = new UploadFile("test1.jpg", 100L);
        File returnFile = new File(uploadFile);
        when(fileRepository.save(any()))
                .thenReturn(returnFile);

        //when, then
        fileService.upload(uploadFile);

    }
}