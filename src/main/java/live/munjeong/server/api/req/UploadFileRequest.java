package live.munjeong.server.api.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UploadFileRequest {
    @NotEmpty
    private List<MultipartFile> files;
}
