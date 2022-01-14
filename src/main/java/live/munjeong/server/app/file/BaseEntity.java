package live.munjeong.server.app.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public abstract class BaseEntity {
    @CreatedDate
    private LocalDateTime createdDate;
    @CreatedBy
    @Column(columnDefinition = "varchar(255) default 'SYSTEM'")
    private String createBy;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @LastModifiedBy
    @Column(columnDefinition = "varchar(255) default 'SYSTEM'")
    private String lastModifiedBy;
    @Column(columnDefinition = "varchar(1) default 'Y'")
    private char useYn;
    @Column(columnDefinition = "varchar(1) default 'N'")
    private char delYn;
}
