package live.munjeong.server.app.file;

import live.munjeong.server.app.common.valid.FileExtension;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@ToString
@Getter
@Setter
public class UploadFile {
    // 업로드 파일 명
    @NotBlank(message = "파일이 없습니다.")
    private String uploadNm;

    // 파일 사이즈
    @Positive(message = "파일 사이즈 에러입니다.")
    private Long size;

    // 파일 확장자
    @FileExtension(message = "허용되지 않은 파일 확장자 입니다.")
    private String extension;

    public UploadFile(String uploadNm, Long size) {
        this.uploadNm = uploadNm;
        this.size = size;
        this.extension = StringUtils.getFilenameExtension(uploadNm);
    }
}
