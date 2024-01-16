package top.aenlly.acg.config.captcha;

import com.anji.captcha.service.CaptchaCacheService;
import com.anji.captcha.service.impl.CaptchaCacheServiceMemImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcgCaptchaConfiguration {

    @Bean
    public CaptchaCacheService captchaCacheService() {
        return new CaptchaCacheServiceMemImpl();
    }

}
