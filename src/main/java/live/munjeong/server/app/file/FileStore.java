package live.munjeong.server.app.file;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.io.File.separator;

@Slf4j
@PropertySource(value = "classpath:file.properties")
@Component
public class FileStore {
    private final String fileDir;

    public FileStore(@Value("${file.dir}") String fileDir) {
        this.fileDir = fileDir;
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

    /**
     * 파일을 실제 저장소에 저장하기
     * @param multipartFile Controller에서 넘어오는 MultipartFile객체
     * @return 저장 성공 시 File 엔티티를 리턴한다.
     * @throws IOException transferTo 에러시 IOException
     */
    public File storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originNm = multipartFile.getOriginalFilename();
        long size = multipartFile.getSize();
        File file = new File(originNm, size);

        multipartFile.transferTo(getRealFile(file));
        return file;
    }

    /**
     * 파일 실제 삭제
     */
    public boolean deleteFile(File file) {
        if(file == null) {
            log.error("delete file is null!!");
            return false;
        }
        log.debug("deleteFile FileId [{}]", file.getId());
        java.io.File realFile = getRealFile(file);
        return realFile.delete();
    }


    /**
     * 파일의 전체경로를 가져온다.
     * @return String
     */
    public String getFullPath(File file) {
        if( !StringUtils.hasText(file.getStoragePath())
                || !StringUtils.hasText(file.getStorageNm())) {
            throw new NoSuchElementException("파일을 찾을 수 없습니다.");
        }
        return fileDir + separator + file.getStoragePath() + file.getStorageNm();
    }

    public java.io.File getRealFile(File file) {
        return new java.io.File(getFullPath(file));
    }
}
