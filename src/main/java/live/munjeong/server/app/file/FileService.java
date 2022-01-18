package live.munjeong.server.app.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class FileService {
    private final FileRepository fileRepository;

    private final FileStore fileStore;

    /**
     * 파일을 실제로 저장 후 DB 에도 저장
     * @param uploadFile controller에서 넘어온 MultipartFile객체
     * @return File
     * @throws IOException
     */
    public File upload(MultipartFile uploadFile) throws IOException {
        log.debug("uploadFile : " + uploadFile);
        File file = fileStore.storeFile(uploadFile);
        return fileRepository.save(file);
    }

    /**
     * 파일을 실제로 삭제 후 DB 에도 삭제
     * @param fileId file의 pk
     */
    public void delete(Long fileId) {
        log.debug("deleteFile fileId [{}] ", fileId);
        Optional<File> file = fileRepository.findById(fileId);
        fileStore.deleteFile(file.orElse(null));
        fileRepository.deleteById(fileId);
    }
}

