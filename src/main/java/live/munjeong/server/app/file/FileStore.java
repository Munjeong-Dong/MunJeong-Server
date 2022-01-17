package live.munjeong.server.app.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FileStore {
    @Value("${file.dir}")
    private final String fileDir;

    public String getFullPath(File file) {
        return fileDir + file.getStoragePath() + file.getStorageNm();
    }

    public List<File> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<File> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public File storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originNm = multipartFile.getOriginalFilename();
        long size = multipartFile.getSize();
        File file = new File(originNm, size);

        multipartFile.transferTo(new java.io.File(getFullPath(file)));
        return file;
    }
}
