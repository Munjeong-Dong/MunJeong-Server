package live.munjeong.server.app.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;

    public Long upload(UploadFile uploadFile) {
        log.debug("uploadFile : " + uploadFile);
        File file = new File(uploadFile);
        fileRepository.save(file);
        return file.getId();
    }
}
