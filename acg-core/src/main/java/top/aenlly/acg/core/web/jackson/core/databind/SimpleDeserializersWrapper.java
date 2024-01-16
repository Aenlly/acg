package top.aenlly.acg.core.web.jackson.core.databind;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.type.ClassKey;
import lombok.extern.slf4j.Slf4j;

/**
 * 反序列化器包装
 *
 * @author Aenlly||tnw
 * @create 2022/10/20 09:11:13
 * @since 1.0.0
 */
@Slf4j
public class SimpleDeserializersWrapper extends SimpleDeserializers {


    private static final long serialVersionUID = 6728146627616665419L;

    @Override
    public JsonDeserializer<?> findEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc)
            throws JsonMappingException {
        JsonDeserializer<?> enumDeserializer = super.findEnumDeserializer(type, config, beanDesc);

        if (enumDeserializer != null) {
            return enumDeserializer;
        }
        for (Class<?> typeInterface : type.getInterfaces()) {
            /*
             import com.fasterxml.jackson.databind.type.ClassKey; // 正确
             import com.fasterxml.classmate.util.ClassKey; // 这个是错误的
             注意jackson里面有两个相同的类名，如果使用错误，可能得不到自己想要的结果。
             */
            enumDeserializer = this._classMappings.get(new ClassKey(typeInterface));
            if (enumDeserializer != null) {
                log.info("\n====>重写枚举查找逻辑[{}]", enumDeserializer);
                return enumDeserializer;
            }
        }
        return null;

    }
}
