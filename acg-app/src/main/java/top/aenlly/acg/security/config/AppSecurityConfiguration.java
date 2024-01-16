package top.aenlly.acg.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import top.aenlly.acg.config.security.AuthorizeRequestsCustomizer;

/**
 * System 模块的 Security 配置
 * <p>
 * 认证url配置
 */
@Configuration("appSystemSecurityConfiguration")
public class AppSecurityConfiguration {

    @Bean("appSystemAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                //                // 登录的接口
                //                registry.antMatchers(buildAdminApi("/system/auth/login")).permitAll();
                //                registry.antMatchers(buildAdminApi("/system/auth/logout")).permitAll();
                //                registry.antMatchers(buildAdminApi("/system/auth/refresh-token")).permitAll();
                //                // 登录登录的接口
                //                registry.antMatchers(buildAdminApi("/system/auth/sms-login")).permitAll();
                //                registry.antMatchers(buildAdminApi("/system/auth/send-sms-code")).permitAll();
                //                // 验证码的接口
                //                registry.antMatchers(buildAdminApi("/system/captcha/**")).permitAll();
                //                // 短信回调 API
                //                registry.antMatchers(buildAdminApi("/system/sms/callback/**")).permitAll();
                //                // OAuth2 API
                //                registry.antMatchers(buildAdminApi("/system/oauth2/token")).permitAll();
                //                registry.antMatchers(buildAdminApi("/system/oauth2/check-token")).permitAll();
                // Swagger 接口文档
                registry.antMatchers("/swagger-ui.html").anonymous().antMatchers("/swagger-resources/**").anonymous().antMatchers(
                        "/webjars/**").anonymous().antMatchers("/*/api-docs").anonymous();
                // Spring Boot Actuator 的安全配置
                registry.antMatchers("/actuator").anonymous().antMatchers("/actuator/**").anonymous();
                // Druid 监控
                registry.antMatchers("/druid/**").anonymous();
                // app-api-认证
                //                registry.antMatchers("/app-api/**").authenticated();

                // 用户鉴权放行接口
                //todo
                //图像验证码、短信验证码
                registry.antMatchers(this.buildAppApi("/captcha/get-image")).permitAll();
                registry.antMatchers(this.buildAppApi("/sms-send/app/send-sms-code")).permitAll();
                // 获取公钥等信息
                registry.antMatchers(this.buildAppApi("/system/secret/get-secret-info")).permitAll();

                //用户相关接口
                registry.antMatchers(this.buildAppApi("/user/common/register")).permitAll();
                registry.antMatchers(this.buildAppApi("/user/auth/phone-login")).permitAll();
                registry.antMatchers(this.buildAppApi("/user/auth/phone-sms-login")).permitAll();
                registry.antMatchers(this.buildAppApi("/user/auth/resetPassword")).permitAll();

            }

        };
    }


}
