package live.munjeong.server.app.file.valid;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;
import java.util.List;
@ExtendWith(MockitoExtension.class)
class FileExtensionValidatorTest {
    @Mock
    private ConstraintValidatorContext constraintValidatorContext;

    @Test
    @DisplayName("파일 패턴에 맞는지 확인")
    void isValid() {
        // given
        List<String> allowedExtension = List.of("*");
        List<String> prohibitedExtension = List.of("jpg");
        FileExtensionValidator fileExtensionValidator = new FileExtensionValidator(allowedExtension,prohibitedExtension);
        String extension = "jpg";

        // when
        boolean result = fileExtensionValidator.isValid(extension, constraintValidatorContext);

        // then
        Assertions.assertThat(result).isFalse();
    }
}