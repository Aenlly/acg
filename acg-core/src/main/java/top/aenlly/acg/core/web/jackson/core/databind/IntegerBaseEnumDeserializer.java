package top.aenlly.acg.core.web.jackson.core.databind;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import top.aenlly.acg.common.enums.IntegerBaseEnum;

import java.lang.reflect.Field;

/**
 * IntegerBaseEnum反序列转换器，序列化用jackson原生的
 *
 * @author Aenlly||tnw
 * @create 2022/10/20 09:36:07
 * @since 1.0.0
 */
@Slf4j
public class IntegerBaseEnumDeserializer extends JsonDeserializer<IntegerBaseEnum> {
    @Override
    public IntegerBaseEnum deserialize(JsonParser jp, DeserializationContext ctxt) {
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
                throw new RuntimeException("自定义枚举反序列化错误:json的这个字段[" + parsingContext.getParent() + "]没有值。枚举反序列化失败,注意该属性不可以使用lombok的注解，如@NonNull等");
            }
//            if (BeanUtil.isEmpty(currentName)) { currentName = parent.getCurrentName(); }

            // 通过对象和属性名获取属性的类型
            Field field = ReflectionUtils.findField(currentValue.getClass(), currentName);
            Class enumClass = field.getType();
            IntegerBaseEnum anEnum = DefaultInputJsonToEnum.getIntegerBaseEnum(inputParameter, enumClass);
            log.debug("\n====>反序列化枚举[{}]==>[{}.{}]", inputParameter, anEnum.getClass(), anEnum);
            return anEnum;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
