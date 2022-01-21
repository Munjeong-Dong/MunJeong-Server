package live.munjeong.server.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import live.munjeong.server.app.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@ToString(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
@TableGenerator(
        name = "FEED_SEQ_GENERATOR",
        table = "TB_SEQUENCES",
        pkColumnValue = "FEED_SEQ",
        allocationSize = 1)
@Entity
public class Feed extends BaseEntity {
    @Id
    @GeneratedValue(generator = "FEED_SEQ_GENERATOR", strategy = GenerationType.TABLE)
    @Column(name = "feed_id", nullable = false)
    private Long id;
    
    //제목
    private String title;

    //내용
    private String content;

    // 작성자
    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //첨부파일들
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files = new ArrayList<>();
}
