package top.aenlly.acg.common.validation;

import cn.hutool.core.util.IdcardUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Aenlly||tnw
 * @create 2022/11/17 16:55
 * @since 1.0.0
 */

public class IdCardCheckValidator implements ConstraintValidator<IdCardCheck, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return IdcardUtil.isValidCard(value);
    }
}
