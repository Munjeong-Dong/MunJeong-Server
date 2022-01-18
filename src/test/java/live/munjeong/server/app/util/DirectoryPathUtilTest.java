package live.munjeong.server.app.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static java.io.File.separator;
import static org.junit.jupiter.api.Assertions.*;

class DirectoryPathUtilTest {
    @Test
    @DisplayName("getDirectoryPath 메서드 테스트")
    void getDirectoryPathTest() {
        //given
        String path1 = "root/";
        String path2 = "Image\\";
        String path3 = "test";
        String path4 = "last";
        String directoryPath = DirectoryPathUtil.getDirectoryPath(path1, path2, path3, path4);

        Assertions.assertThat(directoryPath).isEqualTo("root" + separator + "Image" + separator + "test" + separator + "last" + separator);
    }

}