package live.munjeong.server.app.util;


import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static java.io.File.*;

public class DirectoryPathUtil {
    @Getter
    public enum DirDateType {
        DATE_POLICY_YYYY_MM_DD("yyyy"+separator+"MM"+separator+"dd"+separator),
        DATE_POLICY_YYYY_MM("yyyy"+separator+"MM"+separator),
        DATE_POLICY_YYYY("yyyy"+separator);

        private String pattern;

        DirDateType(String pattern) {
            this.pattern = pattern;
        }
    }

    public static String addTodayDirectoryPath(String path) {
        return addTodayDirectoryPath(path, DirDateType.DATE_POLICY_YYYY_MM_DD);
    }

    public static String addTodayDirectoryPath(String path, DirDateType dateType) {
        String format = LocalDate.now().format(DateTimeFormatter.ofPattern(dateType.getPattern()));
        return getDirectoryPath(path, format);

    }

    public static String getDirectoryPath(String... path) {
        StringBuilder sb = new StringBuilder();

        for (String str : path) {
            if(!StringUtils.hasText(str)) continue;

            sb.append(setLastChatSeparator(str));
        }
        return sb.toString();
    }

    /**
     * 문자열의 마지막 문자에  File.separator 추가
     * @param str 문자열
     * @return File.separator 추가한 문자열
     */
    private static String setLastChatSeparator(@NotBlank String str) {
        char lastChar = str.charAt(str.length() - 1);

        if (lastChar == separatorChar) {
            return str;
        } else if(lastChar == '/' || lastChar == '\\') {
            return str.substring(0, str.length() - 1) + separator;
        } else {
            return str + separator;
        }
    }
}
