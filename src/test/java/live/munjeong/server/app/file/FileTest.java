package live.munjeong.server.app.file;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {
    @Test
    @DisplayName("UploadFile 객체로 생성자 테스트")
    void constructorTestByUploadFile() {
        UploadFile upLoadFile = new UploadFile("testFile1.JPg",540L);
        File file = new File(upLoadFile);
        Assertions.assertThat(file.getFileType()).isEqualTo(FileType.IMAGE);
        Assertions.assertThat(file.getExtensions()).isEqualTo("jpg");
    }

}