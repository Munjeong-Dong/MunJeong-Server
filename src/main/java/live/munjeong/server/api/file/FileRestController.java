package live.munjeong.server.api.file;

import live.munjeong.server.api.common.Result;
import live.munjeong.server.api.req.UploadFileRequest;
import live.munjeong.server.app.domain.File;
import live.munjeong.server.app.file.FileService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileRestController {
    private final FileService fileService;

    @PostMapping("/upload")
    public Result<List<File>> fileUpload(@Valid UploadFileRequest uploadFileRequest) throws IOException {
        log.debug("file upload size [{}]", uploadFileRequest.getFiles().size());
        List<Long> uploadFileId = fileService.upload(uploadFileRequest.getFiles());
        List<File> returnFiles = fileService.findFiles(uploadFileId);
        return new Result<>(returnFiles);
    }
}
