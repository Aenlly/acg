package top.aenlly.acg.config.operatelog;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.aenlly.acg.core.operatelog.core.aop.OperateLogAspect;
import top.aenlly.acg.core.operatelog.core.api.OperateLogApi;
import top.aenlly.acg.core.operatelog.core.service.OperateLogFrameworkService;
import top.aenlly.acg.core.operatelog.core.service.OperateLogFrameworkServiceImpl;

@Configuration
public class AcgOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

    @Bean
    @ConditionalOnMissingBean
    public OperateLogApi operateLogApi() {
        return createReqDTO -> {};
    }

}
