package live.munjeong.server.app.config;

import live.munjeong.server.app.cons.Constant;
import live.munjeong.server.app.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Component
public class BaseEntityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(requestAttributes == null) return Optional.of(Constant.SYSTEM);
        return Optional.of(StringUtils.defaultString(SessionUtil.getNm(), Constant.SYSTEM));
    }
}
