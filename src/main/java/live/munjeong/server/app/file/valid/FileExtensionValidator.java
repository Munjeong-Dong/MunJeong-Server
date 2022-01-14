package live.munjeong.server.app.file.valid;

import live.munjeong.server.app.file.UploadFile;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class FileExtensionValidator implements ConstraintValidator<FileExtension, String> {

    @Value("${file.allowed.Extension}")
    private List<String> allowedExtension;
    @Value("${file.prohibited.Extension}")
    private List<String> prohibitedExtension;

    @Override
    public boolean isValid(String extension, ConstraintValidatorContext context) {
        if(!StringUtils.hasText(extension)) return false;
        if(isProhibitedExtensionPattern(extension)) return false;
        return isAllowedExtensionPattern(extension);
    }

    private boolean isAllowedExtensionPattern(String extension) {
        return allowedExtension.stream()
                .anyMatch(pattern ->
                        PatternMatchUtils.simpleMatch(pattern, extension));
    }

    private boolean isProhibitedExtensionPattern(String extension) {
        return prohibitedExtension.stream()
                .anyMatch(pattern ->
                        PatternMatchUtils.simpleMatch(pattern, extension));
    }

}



