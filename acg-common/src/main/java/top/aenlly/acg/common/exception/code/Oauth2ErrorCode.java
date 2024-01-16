package top.aenlly.acg.common.exception.code;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * System 错误码枚举类
 * <p>
 * system 系统，使用 1-002-000-000 段
 */
@AllArgsConstructor
@Getter
public enum Oauth2ErrorCode implements ErrorCode {

    // ========== OAuth2 客户端 1002020000 =========
    OAUTH2_CLIENT_NOT_EXISTS(1002020000, "OAuth2 客户端不存在"),
    OAUTH2_CLIENT_EXISTS(1002020001, "OAuth2 客户端编号已存在"),
    OAUTH2_CLIENT_DISABLE(1002020002, "OAuth2 客户端已禁用"),
    OAUTH2_CLIENT_AUTHORIZED_GRANT_TYPE_NOT_EXISTS(1002020003, "不支持该授权类型"),
    OAUTH2_CLIENT_SCOPE_OVER(1002020004, "授权范围过大"),
    OAUTH2_CLIENT_REDIRECT_URI_NOT_MATCH(1002020005, "无效 redirect_uri: {}"),
    OAUTH2_CLIENT_CLIENT_SECRET_ERROR(1002020006, "无效 client_secret: {}"),

    // ========== OAuth2 授权 1002021000 =========
    OAUTH2_GRANT_CLIENT_ID_MISMATCH(1002021000, "client_id 不匹配"),
    OAUTH2_GRANT_REDIRECT_URI_MISMATCH(1002021001, "redirect_uri 不匹配"),
    OAUTH2_GRANT_STATE_MISMATCH(1002021002, "state 不匹配"),
    OAUTH2_GRANT_CODE_NOT_EXISTS(1002021003, "code 不存在"),

    // ========== OAuth2 授权 1002022000 =========
    OAUTH2_CODE_NOT_EXISTS(1002022000, "code 不存在"),
    OAUTH2_CODE_EXPIRE(1002022000, "code 已过期"),


    SIGNATURE_VERIFICATION_FAILED(1002022003, "验签失败"),

    ;

    private int code;
    private String msg;
}
