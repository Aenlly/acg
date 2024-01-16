package top.aenlly.acg.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * TalApplyQueryErrorCode
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum OpenWeappErrorCode implements ErrorCode {

    // ==========  通用流程处理 模块 1-009-000-000 ==========
    WX_ERROR_EXCEPTION(1104080001, "微信url获取打开跳转微信小程序参数异常"),
    ;

    private int code;
    private String msg;
}
