package top.aenlly.acg.config.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.aenlly.acg.common.enums.IntegerBaseEnum;
import top.aenlly.acg.common.enums.StringBaseEnum;
import top.aenlly.acg.common.utils.json.JsonUtils;
import top.aenlly.acg.core.web.jackson.core.databind.*;

import java.time.LocalDateTime;

@Slf4j
@Configuration
public class AcgJacksonAutoConfiguration {

    @Bean
    public BeanPostProcessor objectMapperBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (!(bean instanceof ObjectMapper)) {
                    return bean;
                }
                ObjectMapper objectMapper = (ObjectMapper) bean;
                SimpleModule simpleModule = new SimpleModule();
                /*
                 * 解决simpleModule直接添加自定义枚举反序列不生效问题
                 */
                SimpleDeserializersWrapper deserializers = new SimpleDeserializersWrapper();
                deserializers.addDeserializer(IntegerBaseEnum.class, new IntegerBaseEnumDeserializer());
                deserializers.addDeserializer(StringBaseEnum.class, new StringBaseEnumDeserializer());
                simpleModule.setDeserializers(deserializers);
                /*
                 * 1. 新增Long类型序列化规则，数值超过2^53-1，在JS会出现精度丢失问题，因此Long自动序列化为字符串类型
                 * 2. 新增LocalDateTime序列化、反序列化规则
                 */
                simpleModule
                        //                .addSerializer(Long.class, ToStringSerializer.instance)
                        //                    .addSerializer(Long.TYPE, ToStringSerializer.instance)
                        .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                        .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);

                objectMapper.registerModules(simpleModule);

                JsonUtils.init(objectMapper);
                log.info("初始化 jackson 自动配置");
                return bean;
            }
        };
    }
}
