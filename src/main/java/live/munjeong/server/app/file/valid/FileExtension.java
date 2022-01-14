package live.munjeong.server.app.file.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = FileExtensionValidator.class)
@Documented
public @interface FileExtension {
    String message() default "파일 확장자 에러입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
