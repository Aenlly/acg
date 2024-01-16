package top.aenlly.acg.config.security;

import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import top.aenlly.acg.common.oauth2.BaseOAuth2TokenApi;
import top.aenlly.acg.common.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import top.aenlly.acg.common.oauth2.dto.OAuth2AccessTokenDTO;
import top.aenlly.acg.common.permission.BasePermissionApi;
import top.aenlly.acg.core.properties.security.SecurityProperties;
import top.aenlly.acg.core.security.core.aop.PreAuthenticatedAspect;
import top.aenlly.acg.core.security.core.context.TransmittableThreadLocalSecurityContextHolderStrategy;
import top.aenlly.acg.core.security.core.filter.TokenAuthenticationFilter;
import top.aenlly.acg.core.security.core.handler.AccessDeniedHandlerImpl;
import top.aenlly.acg.core.security.core.handler.AuthenticationEntryPointImpl;
import top.aenlly.acg.core.security.core.service.SecurityFrameworkService;
import top.aenlly.acg.core.security.core.service.SecurityFrameworkServiceImpl;
import top.aenlly.acg.core.web.handler.GlobalExceptionHandler;

import javax.annotation.Resource;

/**
 * Spring Security 自动配置类，主要用于相关组件的配置
 * <p>
 * 注意，不能和 {@link AcgWebSecurityConfigurerAdapter} 用一个，原因是会导致初始化报错。
 * 参见 https://stackoverflow.com/questions/53847050/spring-boot-delegatebuilder-cannot-be-null-on-autowiring-authenticationmanager 文档。
 *
 * @author anonymous
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(SecurityProperties.class)
public class AcgSecurityAutoConfiguration {

    @Resource
    private SecurityProperties securityProperties;

    /**
     * 处理用户未登录拦截的切面的 Bean
     */
    @Bean
    public PreAuthenticatedAspect preAuthenticatedAspect() {
        return new PreAuthenticatedAspect();
    }

    /**
     * 认证失败处理类 Bean
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 权限不够处理器 Bean
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    /**
     * Spring Security 加密器
     * 考虑到安全性，这里采用 BCryptPasswordEncoder 加密器
     *
     * @see <a href="http://stackabuse.com/password-encoding-with-spring-security/">Password Encoding with Spring Security</a>
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public BaseOAuth2TokenApi baseOAuth2TokenApi() {
        return new BaseOAuth2TokenApi() {
            @Override
            public OAuth2AccessTokenCheckRespDTO checkAccessToken(String accessToken) {
                return null;
            }

            @Override
            public OAuth2AccessTokenDTO getAccessToken(String accessToken) {
                return null;
            }
        };
    }

    /**
     * Token 认证过滤器 Bean
     */
    @Bean
    public TokenAuthenticationFilter authenticationTokenFilter(GlobalExceptionHandler globalExceptionHandler,
            BaseOAuth2TokenApi baseOauth2TokenApi) {
        return new TokenAuthenticationFilter(securityProperties, globalExceptionHandler, baseOauth2TokenApi);
    }


    @Bean
    @ConditionalOnMissingBean
    public BasePermissionApi basePermissionApi() {
        return new BasePermissionApi() {
            @Override
            public boolean hasAnyPermissions(Long userId, String... permissions) {
                return true;
            }

            @Override
            public boolean hasAnyRoles(Long userId, String... roles) {
                return true;
            }
        };
    }

    @Bean("ss") // 使用 Spring Security 的缩写，方便使用
    public SecurityFrameworkService securityFrameworkService(BasePermissionApi basePermissionApi) {
        return new SecurityFrameworkServiceImpl(basePermissionApi);
    }

    /**
     * 声明调用 {@link SecurityContextHolder#setStrategyName(String)} 方法，
     * 设置使用 {@link TransmittableThreadLocalSecurityContextHolderStrategy} 作为 Security 的上下文策略
     */
    @Bean
    public MethodInvokingFactoryBean securityContextHolderMethodInvokingFactoryBean() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
        methodInvokingFactoryBean.setTargetMethod("setStrategyName");
        methodInvokingFactoryBean.setArguments(TransmittableThreadLocalSecurityContextHolderStrategy.class.getName());
        return methodInvokingFactoryBean;
    }

}
