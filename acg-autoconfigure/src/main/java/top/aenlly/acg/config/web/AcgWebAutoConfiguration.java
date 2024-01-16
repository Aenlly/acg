package top.aenlly.acg.config.web;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.aenlly.acg.common.enums.WebFilterOrderEnum;
import top.aenlly.acg.core.properties.web.WebProperties;
import top.aenlly.acg.core.properties.xss.XssProperties;
import top.aenlly.acg.core.service.ApiErrorLogFrameworkService;
import top.aenlly.acg.core.web.filter.CacheRequestBodyFilter;
import top.aenlly.acg.core.web.filter.LoggerFilter;
import top.aenlly.acg.core.web.filter.XssFilter;
import top.aenlly.acg.core.web.handler.GlobalExceptionHandler;
import top.aenlly.acg.core.web.handler.GlobalResponseBodyHandler;
import top.aenlly.acg.core.web.utils.WebFrameworkUtils;

import javax.annotation.Resource;
import javax.servlet.Filter;

@Configuration
@EnableConfigurationProperties({WebProperties.class, XssProperties.class})
public class AcgWebAutoConfiguration implements WebMvcConfigurer {

    @Resource
    private WebProperties webProperties;
    /**
     * 应用名
     */
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurePathMatch(configurer, webProperties.getAdminApi());
        configurePathMatch(configurer, webProperties.getAppApi());
    }

    /**
     * 设置 API 前缀，仅仅匹配 controller 包下的
     *
     * @param configurer 配置
     * @param api        API 配置
     */
    private void configurePathMatch(PathMatchConfigurer configurer, WebProperties.Api api) {
        AntPathMatcher antPathMatcher = new AntPathMatcher(".");
        configurer.addPathPrefix(api.getPrefix(), clazz -> clazz.isAnnotationPresent(RestController.class)
                && antPathMatcher.match(api.getController(), clazz.getPackage().getName())); // 仅仅匹配 controller 包
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler(ApiErrorLogFrameworkService apiErrorLogFrameworkService) {
        return new GlobalExceptionHandler(applicationName, apiErrorLogFrameworkService);
    }

    @Bean
    public GlobalResponseBodyHandler globalResponseBodyHandler() {
        return new GlobalResponseBodyHandler();
    }

    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public WebFrameworkUtils webFrameworkUtils(WebProperties webProperties) {
        // 由于 WebFrameworkUtils 需要使用到 webProperties 属性，所以注册为一个 Bean
        return new WebFrameworkUtils(webProperties);
    }

    // ========== Filter 相关 ==========

    /**
     * 创建 CorsFilter Bean，解决跨域问题
     */
//    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterBean() {
        // 创建 CorsConfiguration 对象
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
//
//        webProperties.getOriginPattern().forEach(config::addAllowedOriginPattern);
        config.addAllowedOriginPattern("*"); // 设置访问源地址
        config.addAllowedHeader("*"); // 设置访问源请求头
        config.addAllowedMethod("*"); // 设置访问源请求方法
        // 创建 UrlBasedCorsConfigurationSource 对象
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 对接口配置跨域设置
        return createFilterBean(new CorsFilter(source), WebFilterOrderEnum.CORS_FILTER);
    }

    /**
     * 创建 RequestBodyCacheFilter Bean，可重复读取请求内容
     */
    @Bean
    public FilterRegistrationBean<CacheRequestBodyFilter> requestBodyCacheFilter() {
        return createFilterBean(new CacheRequestBodyFilter(), WebFilterOrderEnum.REQUEST_BODY_CACHE_FILTER);
    }

    /**
     * 创建 XssFilter Bean，解决 Xss 安全问题
     */
    @Bean
    public FilterRegistrationBean<XssFilter> xssFilter(XssProperties properties, PathMatcher pathMatcher) {
        return createFilterBean(new XssFilter(properties, pathMatcher), WebFilterOrderEnum.XSS_FILTER);
    }

    /**
     * 创建 DemoFilter Bean，演示模式
     */
//    @Bean
//    @ConditionalOnProperty(value = "rencai.demo", havingValue = "true")
//    public FilterRegistrationBean<DemoFilter> demoFilter() {
//        return createFilterBean(new DemoFilter(), WebFilterOrderEnum.DEMO_FILTER);
//    }
    @Bean
//    @ConditionalOnProperty(prefix = "rencai.access-log", value = "enable", matchIfMissing = true)
    public FilterRegistrationBean<LoggerFilter> loggerFilter() {
        return createFilterBean(new LoggerFilter(), WebFilterOrderEnum.LOGGER_FILTER);
    }

    @Bean
    @ConditionalOnProperty(prefix = "rencai.sentinel", value = "enable", havingValue = "true", matchIfMissing = false)
    public FilterRegistrationBean<CommonFilter> sentinelFilter() {
        return createFilterBean(new CommonFilter(), WebFilterOrderEnum.SENTINEL_FILTER);
    }

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }

}
