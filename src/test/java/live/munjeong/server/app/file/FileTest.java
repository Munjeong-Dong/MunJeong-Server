package live.munjeong.server.app.file;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {
    @Test
    @DisplayName("UploadFile 객체로 생성자 테스트")
    void constructorTestByUploadFile() {
        UpLoadFile upLoadFile = new UpLoadFile("testFile1.JPg",540L);
        File file = new File(upLoadFile);
        Assertions.assertThat(file.getFileType()).isEqualTo(FileType.IMAGE);
        Assertions.assertThat(file.getExtensions()).isEqualTo(FileType.IMAGE);
    }

}