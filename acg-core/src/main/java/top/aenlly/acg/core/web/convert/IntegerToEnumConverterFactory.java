package top.aenlly.acg.core.web.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import top.aenlly.acg.common.enums.IntegerBaseEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 整型 --》枚举 转换器工厂类
 *
 * <p>因为SpringBoot映射枚举时，默认用枚举名称映射
 *
 * <p>使用值映射需要进行转换 需要在springMVC中注册
 *
 * @author Aenlly||tnw
 * @create 2022/10/12 14:00:58
 * @since 1.0.0
 */
public class IntegerToEnumConverterFactory implements ConverterFactory<String, IntegerBaseEnum> {
    private static final Map<Class, Converter> CONVERTERS = new HashMap<>();

    /**
     * 获取一个从 Integer 转化为 T 的转换器，T 是一个泛型，有多个实现
     *
     * @param targetType 转换后的类型
     * @return 返回一个转化器
     */
    @Override
    public <T extends IntegerBaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter<String, T> converter = CONVERTERS.get(targetType);
        if (converter == null) {
            converter = new IntegerToEnumConverter<>(targetType);
            CONVERTERS.put(targetType, converter);
        }
        return converter;
    }
}
