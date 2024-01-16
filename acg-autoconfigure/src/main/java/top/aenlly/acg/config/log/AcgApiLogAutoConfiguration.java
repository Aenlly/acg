package top.aenlly.acg.config.log;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.aenlly.acg.common.enums.WebFilterOrderEnum;
import top.aenlly.acg.common.utils.json.JsonUtils;
import top.aenlly.acg.common.utils.log.LogStringUtils;
import top.aenlly.acg.config.log.filter.ApiAccessLogFilter;
import top.aenlly.acg.config.web.AcgWebAutoConfiguration;
import top.aenlly.acg.core.logger.ApiAccessLogApi;
import top.aenlly.acg.core.logger.ApiErrorLogApi;
import top.aenlly.acg.core.properties.web.WebProperties;
import top.aenlly.acg.core.service.ApiAccessLogFrameworkService;
import top.aenlly.acg.core.service.ApiAccessLogFrameworkServiceImpl;
import top.aenlly.acg.core.service.ApiErrorLogFrameworkService;
import top.aenlly.acg.core.service.ApiErrorLogFrameworkServiceImpl;

import javax.servlet.Filter;

/**
 * @author Aenlly
 */
@Configuration
@AutoConfigureAfter(AcgWebAutoConfiguration.class)
public class AcgApiLogAutoConfiguration {

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }

    @Bean
    @ConditionalOnMissingBean
    public ApiAccessLogApi apiAccessLogApi() {
        return createDTO -> {
            createDTO.setResponseBody(LogStringUtils.shortBody(createDTO.getResponseBody()));
            ApiAccessLogApi.logger.info(JsonUtils.toJsonString(createDTO));
        };
    }

    @Bean
    @ConditionalOnMissingBean
    public ApiErrorLogApi apiErrorLogApi() {
        return createDTO -> {
            //不打印堆栈信息，堆栈信息可以根据日志号去其他日志查询
            createDTO.setExceptionStackTrace(null);
            ApiErrorLogApi.logger.info(JsonUtils.toJsonString(createDTO));
        };
    }

    @Bean
    public ApiAccessLogFrameworkService apiAccessLogFrameworkService(ApiAccessLogApi apiAccessLogApi) {
        return new ApiAccessLogFrameworkServiceImpl(apiAccessLogApi);
    }

    @Bean
    public ApiErrorLogFrameworkService apiErrorLogFrameworkService(ApiErrorLogApi apiErrorLogApi) {
        return new ApiErrorLogFrameworkServiceImpl(apiErrorLogApi);
    }

    /**
     * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
     */
    @Bean
    public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
            @Value("${spring.application.name}") String applicationName,
            ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
    }

}
