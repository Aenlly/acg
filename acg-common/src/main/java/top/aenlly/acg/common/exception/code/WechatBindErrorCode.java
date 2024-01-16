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
public enum WechatBindErrorCode implements ErrorCode {

    // ==========  通用流程处理 模块 1-009-000-000 ==========
    WECHAT_CODE_ERROR(1104050001, "微信code错误"),
    // ==========  通用流程处理 模块 1-009-000-000 ==========
    UUID_KEY_ERROR(1104050002, "uuidKey错误"),

    ;
    private int code;
    private String msg;
}
