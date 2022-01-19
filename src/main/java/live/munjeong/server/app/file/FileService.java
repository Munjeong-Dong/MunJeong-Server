package live.munjeong.server.app.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class FileService {
    private final FileRepository fileRepository;

    private final FileStore fileStore;

    /**
     * 파일을 실제로 저장 후 DB 에도 저장
     * @param uploadFiles controller에서 넘어온 MultipartFile 리스트
     * @return List<Long> 저장된 파일의 키값들
     * @throws IOException IO에러
     */
    public List<Long> upload(List<MultipartFile> uploadFiles) throws IOException {
        List<File> storeFiles = fileStore.storeFiles(uploadFiles);
        List<File> files = fileRepository.saveAll(storeFiles);
        return files.stream().map(File::getId).collect(Collectors.toList());
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

    public List<File> findFiles(List<Long> fileIds) {
        return fileRepository.findAllById(fileIds);
    }
}

