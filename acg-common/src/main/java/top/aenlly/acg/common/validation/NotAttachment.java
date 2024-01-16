package top.aenlly.acg.common.validation;

import top.aenlly.acg.common.pojo.ACL;

import java.lang.annotation.*;

/**
 * 结合{@link NotAttachments} 使用
 *
 * @author Aenlly||tnw
 * @create 2022/10/24 16:16
 * @since 1.0.0
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotAttachment {

    /**
     * @return 要验证的key
     */
    String value();

    ACL acl();

    /**
     * @return 最大长度
     */
    int max();

    /**
     * @return 说明
     */
    String desc() default "";

    /**
     * @return 必须的
     */
    boolean must() default true;

    public enum Shape {
        LIST,
        OBJECT,
        MAP,
    }
}
