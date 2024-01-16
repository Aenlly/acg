package top.aenlly.acg.core.web.jackson.core.databind;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import top.aenlly.acg.common.enums.BaseEnum;
import top.aenlly.acg.common.enums.IntegerBaseEnum;
import top.aenlly.acg.common.enums.StringBaseEnum;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DefaultInputJsonToEnum {
    private static final Map<Class, Map<Object, BaseEnum>> CACHE_ENUM = new HashMap<>();

    public static IntegerBaseEnum getIntegerBaseEnum(String inputParameter, Class enumClass) {
        boolean debugEnabled = log.isDebugEnabled();
        if (debugEnabled) {
            log.debug("\n====>反序列化，当前输入的值[{}]对应的枚举类是[{}]", inputParameter, enumClass);
        }
        try {
            if (CACHE_ENUM.containsKey(enumClass)) {
                IntegerBaseEnum baseEnum = (IntegerBaseEnum) CACHE_ENUM.get(enumClass).get(inputParameter);
                Assert.notNull(baseEnum, () -> new RuntimeException(String.format("枚举反序列化错误，输入参数[%s]找不到对应的枚举值", inputParameter)));
                return baseEnum;
            }
            //  values是默认的方法，必定存在
            Method valuesMethod = enumClass.getDeclaredMethod("values");
            //  通过反射获取该枚举类的所有枚举值
            IntegerBaseEnum[] values = (IntegerBaseEnum[]) valuesMethod.invoke(null);
            Map<Object, BaseEnum> map = new HashMap<>(8);
            for (IntegerBaseEnum value : values) {
                map.put(String.valueOf(value.getValue()), value);
            }
            CACHE_ENUM.put(enumClass, map);
            if (map.containsKey(inputParameter)) {
                return (IntegerBaseEnum) map.get(inputParameter);
            }

            //如果都拿不到,那就直接抛出异常了
            throw new RuntimeException(String.format("枚举反序列化错误，输入参数[%s]找不到对应的枚举值", inputParameter));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static StringBaseEnum getStringBaseEnum(String inputParameter, Class enumClass) {
        boolean debugEnabled = log.isDebugEnabled();
        if (debugEnabled) {
            log.debug("\n====>反序列化，当前输入的值[{}]对应的枚举类是[{}]", inputParameter, enumClass);
        }
        try {
            if (CACHE_ENUM.containsKey(enumClass)) {
                StringBaseEnum baseEnum = (StringBaseEnum) CACHE_ENUM.get(enumClass).get(inputParameter);
                Assert.notNull(baseEnum, () -> new RuntimeException(String.format("枚举反序列化错误，输入参数[%s]找不到对应的枚举值", inputParameter)));
                return baseEnum;
            }
            //  values是默认的方法，必定存在
            Method valuesMethod = enumClass.getDeclaredMethod("values");
            //  通过反射获取该枚举类的所有枚举值
            StringBaseEnum[] values = (StringBaseEnum[]) valuesMethod.invoke(null);
            Map<Object, BaseEnum> map = new HashMap<>();
            for (StringBaseEnum value : values) {
                map.put(value.getValue(), value);
            }
            CACHE_ENUM.put(enumClass, map);
            if (map.containsKey(inputParameter)) {
                return (StringBaseEnum) map.get(inputParameter);
            }

            //如果都拿不到,那就直接抛出异常了
            throw new RuntimeException(String.format("枚举反序列化错误，输入参数[%s]找不到对应的枚举值", inputParameter));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
