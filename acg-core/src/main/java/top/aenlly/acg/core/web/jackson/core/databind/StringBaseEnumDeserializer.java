package top.aenlly.acg.core.web.jackson.core.databind;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import top.aenlly.acg.common.enums.StringBaseEnum;

import java.lang.reflect.Field;

/**
 * 字符串基地enum串并转换器
 *
 * @author Aenlly||tnw
 * @create 2022/10/20 09:37:54
 * @since 1.0.0
 */
@Slf4j
public class StringBaseEnumDeserializer extends JsonDeserializer<StringBaseEnum> {

    @Override
    public StringBaseEnum deserialize(JsonParser jp, DeserializationContext ctxt) {
        try {
            // 前端输入的值
            String inputParameter = jp.getText();
            if (!StringUtils.hasText(inputParameter)) {
                return null;
            }

            JsonStreamContext parsingContext = jp.getParsingContext();
            // 前端注入的对象(ReqDTO)
            Object currentValue = parsingContext.getCurrentValue();
            // 字段名
            String currentName = parsingContext.getCurrentName();
            JsonStreamContext parent = parsingContext.getParent();
            if (currentValue == null) {
                currentValue = parent.getCurrentValue();
            }
            if (currentValue == null) {
                // 可能不能使用lombok的注解，如@NonNull等,当前版本支持注解
                log.error("自定义枚举反序列化错误:json的这个字段[{}]没有值。枚举反序列化失败", parsingContext.getParent());
                throw new RuntimeException("自定义枚举反序列化错误:json的这个字段[" + parsingContext.getParent() + "]没有值。枚举反序列化失败," +
                        "注意该属性不可以使用lombok的注解，如@NonNull等");
            }
//            if (BeanUtil.isEmpty(currentName)) { currentName = parent.getCurrentName(); }
            // 通过对象和属性名获取属性的类型
            Field field = ReflectionUtils.findField(currentValue.getClass(), currentName);
            if (field == null) {
                throw new RuntimeException("反射字段为null：" + currentValue.getClass());
            }
            Class enumClass = field.getType();
            StringBaseEnum anEnum = DefaultInputJsonToEnum.getStringBaseEnum(inputParameter, enumClass);
            log.debug("\n====>反序列化枚举[{}]==>[{}.{}]", inputParameter, anEnum.getClass(), anEnum);
            return anEnum;
        } catch (Exception e) {
            log.info("反序列化错误：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
