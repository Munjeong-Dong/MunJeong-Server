package live.munjeong.server.app.file.valid;

import live.munjeong.server.app.file.UpLoadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

public class FileExtensionValidator implements ConstraintValidator<FileExtension, UpLoadFile> {

    @Value("${file.allowedExtension}")
    private Set<String> allowedExtension;
    @Value("${file.prohibitedExtension}")
    private List<String> prohibitedExtension;

    @Override
    public boolean isValid(UpLoadFile value, ConstraintValidatorContext context) {
        String extension = value.getExtension();
        if(!StringUtils.hasText(extension)) return false;
        if(prohibitedExtension.contains(extension)) return false;
        return allowedExtension.contains(extension);
    }
}



