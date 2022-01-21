package live.munjeong.server.api.common;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * 공통으로 에러일 경우 리턴이다.
 */

@Getter
public class ErrorResponse {
    // 에러에 대한 메세지 이다.
    private String message;
    // HTTP 상태코드이다.
    private int status;
    // 어떤 필드에서 에러 났는지 알려주는 원인이다.
    private List<FieldError> errors;
    // 비즈니스에서 사용할 에러 코드이다.
    private ErrorCode code;

    @Getter
    static class FieldError {
        private String field;
        private String value;
        private String reason;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return null;
    }
    public static ErrorResponse of(Exception e) {
        return null;
    }

    public static ErrorResponse of(ErrorCode errorCode, Exception e) {

        return null;
    }

    public static ErrorResponse of(ErrorCode errorCode, BindingResult result) {

        return null;
    }
}
