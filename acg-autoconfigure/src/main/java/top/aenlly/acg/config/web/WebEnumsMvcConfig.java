package top.aenlly.acg.config.web;

import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.aenlly.acg.core.web.convert.IntegerToEnumConverterFactory;
import top.aenlly.acg.core.web.convert.StringToEnumConverterFactory;

/**
 * web mvc配置
 *
 * @author Aenlly||tnw
 * @create 2022/10/12 13:59:37
 * @since 3.1.0
 */
public class WebEnumsMvcConfig implements WebMvcConfigurer {

    /**
     * 枚举类的转换器工厂 addConverterFactory
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new IntegerToEnumConverterFactory());
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }
}
