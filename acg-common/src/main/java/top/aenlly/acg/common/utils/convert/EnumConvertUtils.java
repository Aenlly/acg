package top.aenlly.acg.common.utils.convert;

import cn.hutool.core.util.ArrayUtil;
import top.aenlly.acg.common.enums.BaseEnum;
import top.aenlly.acg.common.pojo.KeyValuePair;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Aenlly||tnw
 * @create 2022/10/26 10:51
 * @since 1.0.0
 */

public class EnumConvertUtils {
    private static final Map<String, List<KeyValuePair>> CACHE = new HashMap<>();


    /**
     * 转换成list中对象存k-v
     *
     * <p>
     * 只转换一次的枚举则调用该方法
     * </p>
     *
     * @param enumClass
     * @param ignoreEnum
     * @return
     */
    public static List<KeyValuePair> convertList(Class<? extends Enum> enumClass, BaseEnum... ignoreEnum) {
        return convertList(enumClass, enumClass.getName() + 0, ignoreEnum);
    }

    /**
     * 转换成list中对象存k-v
     *
     * @param enumClass
     * @param ignoreEnum 忽略的枚举
     * @return
     */
    private static List<KeyValuePair> convertList(Class<? extends Enum> enumClass, String cacheKey, BaseEnum[] ignoreEnums) {
        if (CACHE.containsKey(cacheKey)) {
            return CACHE.get(cacheKey);
        }

        List<KeyValuePair> list = new ArrayList<>();
        try {
            // values是默认的方法，必定存在
            Method valuesMethod = enumClass.getDeclaredMethod("values");
            // 通过反射获取该枚举类的所有枚举值
            BaseEnum[] enums = (BaseEnum[]) valuesMethod.invoke(null);
            Arrays.stream(enums)
                    .filter(f -> ArrayUtil.firstMatch(type -> type.equals(f), ignoreEnums) == null)
                    .forEach(e -> list.add(new KeyValuePair(e.getValue(), e.getDesc())));
            CACHE.put(cacheKey, list);
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转换成list中对象存k-v
     * <p>
     * 多次转换的枚举则调用该方法
     * </p>
     *
     * @param enumClass             转换类
     * @param cacheKeyAntiHeavyMark 缓存防止重复的标识字段
     * @param ignoreEnum            忽略的枚举
     * @return
     */
    public static List<KeyValuePair> convertList(Class<? extends Enum> enumClass, Integer cacheKeyAntiHeavyMark,
            BaseEnum... ignoreEnum) {
        return convertList(enumClass, enumClass.getName() + cacheKeyAntiHeavyMark, ignoreEnum);
    }
}
