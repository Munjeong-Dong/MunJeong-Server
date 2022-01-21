package live.munjeong.server.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import live.munjeong.server.app.domain.entity.BaseEntity;
import live.munjeong.server.app.file.FileType;
import live.munjeong.server.app.file.UploadFile;
import live.munjeong.server.app.util.DirectoryPathUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.*;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@TableGenerator(
        name = "FILE_SEQ_GENERATOR",
        table = "TB_SEQUENCES",
        pkColumnValue = "FILE_SEQ",
        allocationSize = 1)
@Entity
public class File extends BaseEntity {
    @Id
    @GeneratedValue(generator = "FILE_SEQ_GENERATOR", strategy = GenerationType.TABLE)
    @Column(name = "file_id", nullable = false)
    private Long id;

    // 업로드 파일 명
    private String originNm;
    // 저장 파일 명
    private String storageNm;
    /**
     *  저장 파일 위치
     *  root(file.dir) / 파일 타입 / 년 / 월 / 일 /
     *  ex ) 2022년 1월 14일 test.jpg -> root/IMAGE/2022/01/14/
     */
    private String storagePath;

    // 파일 타입 (IMAGE, VIDEO, ATTACH)
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    // 파일 확장자
    private String extensions;

    //파일 사이즈
    private Long size;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    public File(String originNm, long size) {
        this.originNm = originNm;
        String extension = StringUtils.getFilenameExtension(originNm);
        this.extensions = extension == null? null : extension.toLowerCase();
        this.size = size;
        this.fileType = FileType.getFileType(extensions);

        this.storageNm = UUID.randomUUID().toString();
        this.storagePath = DirectoryPathUtil.addTodayDirectoryPath(fileType.toString());
    }

    public File(UploadFile upLoadFile) {
        this.originNm = upLoadFile.getUploadNm();
        this.extensions = upLoadFile.getExtension().toLowerCase();
        this.size = upLoadFile.getSize();
        this.fileType = FileType.getFileType(extensions);

        this.storageNm = UUID.randomUUID().toString();
        this.storagePath = DirectoryPathUtil.addTodayDirectoryPath(fileType.toString());
    }
}
