package live.munjeong.server.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import live.munjeong.server.app.cons.Constant;
import live.munjeong.server.app.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


@TestConfiguration
@EnableJpaAuditing
public class TestConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of(StringUtils.defaultString(SessionUtil.getNm(), Constant.SYSTEM));
    }
}
