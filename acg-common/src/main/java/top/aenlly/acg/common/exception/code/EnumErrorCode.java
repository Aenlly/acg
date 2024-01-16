package top.aenlly.acg.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: zay
 * @Date: 2023-02-02 9:53
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum EnumErrorCode implements ErrorCode {

    ENUM_CLASS_IS_NULL(1104060001, "enumClass can't be null"),
    ENUM_CONVERT_EXCEPTION(1104060002, "{} can't convert to {}"),
    ENUM_FIELD_MUST_HAVE_ANNOTATION(1104060003, "{} must have a field with the annotation {}"),
    ;
    private int code;
    private String msg;
}
