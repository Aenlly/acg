package top.aenlly.acg.core.web.convert;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.core.convert.converter.Converter;
import top.aenlly.acg.common.enums.StringBaseEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * GET请求时，都是string，所以需要string转换工厂
 *
 * <p>map同时存储Name与Value，以便解析前端GET时传递整型
 *
 * <p>字符串 --》枚举 转换器 因为SpringBoot映射枚举时，默认用枚举名称映射 使用值映射需要进行转换
 *
 * @author Aenlly||tnw
 * @create 2022/10/12 14:02:36
 * @since 1.0.0
 */
public class StringToEnumConverter<T extends StringBaseEnum<?>> implements Converter<String, T> {
    private Map<String, T> enumMap = new HashMap<>();

    public StringToEnumConverter(Class<T> enumType) {
        T[] enums = enumType.getEnumConstants();
        for (T e : enums) {
            this.enumMap.put(e.getValue(), e);
        }
    }

    @Override
    public T convert(String value) {
        T t = this.enumMap.get(value);
        if (ObjectUtil.isNull(t)) {
            throw new IllegalArgumentException(value + "---无法匹配对应的枚举类型");
        }
        return t;
    }
}
