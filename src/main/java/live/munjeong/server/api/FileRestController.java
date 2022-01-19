package live.munjeong.server.api;

import live.munjeong.server.api.common.Result;
import live.munjeong.server.app.file.File;
import live.munjeong.server.app.file.FileService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileRestController {
    private final FileService fileService;

    @PostMapping("/upload")
    public Result fileUpload(@Valid UploadFileReq uploadFileReq) throws IOException {
        log.debug("file upload size [{}]", uploadFileReq.getFiles().size());
        List<Long> uploadFileId = fileService.upload(uploadFileReq.getFiles());
        List<File> returnFiles = fileService.findFiles(uploadFileId);
        return new Result(returnFiles);
    }

    @AllArgsConstructor
    @Data
    static class UploadFileReq {
        @NotEmpty
        private List<MultipartFile> files;
    }


}
