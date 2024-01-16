package top.aenlly.acg.common.validation;

import top.aenlly.acg.common.pojo.ACL;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 单文件与list文件校验
 *
 * @author Aenlly||tnw
 * @create 2022/10/31 12:04
 * @since 1.0.0
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FileCheckValidator.class)
public @interface FileCheck {

    ACL acl();

    /**
     * @return 最大长度
     */
    int max() default 0;

    String message();


    /**
     * @return 必须的
     */
    boolean must() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
