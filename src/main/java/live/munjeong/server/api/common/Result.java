package live.munjeong.server.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 공통 리턴하는 클래스이다.
 */
@AllArgsConstructor
@Data
public class Result <T> {
    private T data;
}
