package top.aenlly.acg.common.exception.code;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * System 错误码枚举类
 * <p>
 * system 系统，使用 1-002-000-000 段
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum DictErrorCode implements ErrorCode {

    // ========== 字典类型 1002006000 ==========
    DICT_TYPE_NOT_EXISTS(1002006001, "当前字典类型不存在"),
    DICT_TYPE_NOT_ENABLE(1002006002, "字典类型不处于开启状态，不允许选择"),
    DICT_TYPE_NAME_DUPLICATE(1002006003, "已经存在该名字的字典类型"),
    DICT_TYPE_TYPE_DUPLICATE(1002006004, "已经存在该类型的字典类型"),
    DICT_TYPE_HAS_CHILDREN(1002006005, "无法删除，该字典类型还有字典数据"),

    // ========== 字典数据 1002007000 ==========
    DICT_DATA_NOT_EXISTS(1002007001, "当前字典数据不存在"),
    DICT_DATA_NOT_ENABLE(1002007002, "字典数据({})不处于开启状态，不允许选择"),
    DICT_DATA_VALUE_DUPLICATE(1002007003, "已经存在该值的字典数据"),

    ;
    private int code;
    private String msg;
}
