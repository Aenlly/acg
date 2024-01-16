package top.aenlly.acg.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 判断map中是否存在该key
 *
 * @author Aenlly||tnw
 * @create 2022/10/27 14:03:18
 * @since 1.0.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NotAttachmentsValidator.class)
public @interface NotAttachments {
    /**
     * @return 集合
     */
    NotAttachment[] value();

    String message() default "必要的附件{value}不存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
