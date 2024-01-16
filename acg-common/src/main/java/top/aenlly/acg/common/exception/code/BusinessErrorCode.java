package top.aenlly.acg.common.exception.code;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description: 业务错误码
 * @author： Lin
 * @Date 2022/7/21 11:21
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum BusinessErrorCode implements ErrorCode {
    /*
    kugga-duke-chat  10
     kugga-duke-common  11
    kugga-duke-league  12
     kugga-duke-pay  13
    kugga-duke-system  14
    kugga-duke-user 15
     kugga-duke-user-cv 16
     kugga-duke-bos 18

     */
    // ========== 系统DB 1001000000 ==========
    DB_UPDATE_FAILED(149000, "数据更新失败"),

    DB_DELETE_FAILED(149001, "数据删除失败"),

    DB_INSERT_FAILED(149002, "数据新增失败"),

    DB_GET_FAILED(149003, "数据查询失败"),


    SYSTEM_ERROR(149004, "系统错误"),

    PARAM_ERROR(149005, "请求参数错误"),

    MONTH_ERROR(149006, ""),

    // ========== AUTH 模块 1002000000 ==========
    AUTH_LOGIN_BAD_CREDENTIALS(150100, "登录失败，账号密码不正确"),

    AUTH_LOGIN_USER_DISABLED(150101, "登录失败，账号被禁用"),

    AUTH_LOGIN_CAPTCHA_NOT_FOUND(150102, "验证码不存在"),

    AUTH_LOGIN_CAPTCHA_CODE_ERROR(150103, "验证码不正确"),

    AUTH_TOKEN_EXPIRED(150104, "Token 已经过期"),

    AUTH_MOBILE_NOT_EXISTS(150105, "手机号不存在"),

    // ========== 用户模块 1002003000 ==========
    USER_USERNAME_EXISTS(150200, "用户账号已经存在"),

    USER_MOBILE_EXISTS(150201, "手机号已经存在"),

    USER_EMAIL_EXISTS(150202, "邮箱已经存在"),

    USER_NOT_EXISTS(150203, "用户不存在"),

    USER_IMPORT_LIST_IS_EMPTY(150204, "导入用户数据不能为空！"),

    USER_PASSWORD_FAILED(150205, "原密码不正确"),

    USER_IS_DISABLE(150206, "名字为【{}】的用户已被禁用"),

    NEW_OLD_PASSWORD_NOT_EQUALS(150207, "新老密码不能一致"),

    USER_COUNT_MAX(150208, "创建用户失败，原因：超过租户最大租户配额({})！"),

    PASSWORD_ENSURE_NOT_MATCH(150209, "密码确认与密码不匹配"),

    USER_REGISTER_COUNT_MAX(150210, "用户注册失败,请稍后重试"),

    USER_ACCOUNT_NOT_EXIST(150211, "用户账户不存在"),

    USER_NUMBER_COUNT_MAX(150212, "用户名num超标"),

    USER_FORGET_PWD_TOKEN_ERROR(150213, "忘记密码token已失效"),

    EMAIL_NOT_NULL(150202, "邮箱不能为空"),

    FAVORITE_NOT_EXISTS(150300, "收藏记录不存在"),

    FAVORITE_EXISTS(150301, "收藏记录已存在"),

    FAVORITE_RECOMMENDATION_LEAGUE_NOT_NULL(150302, "写推荐信公会id和收藏推荐信时公会id不能为空"),

    // ========== 邮件模块 1002006000 ==========
    ILLEGAL_PARAMS(140000, "必输参数不能为空"),


    // ========== 密钥  ==========
    ACQUIRE_KEY_PARIS_FAIL(149200, "密钥获取失败"),

    SECRET_ILLEGAL_PARAMS(149201, "加解密必输参数不能为空"),

    ENCRYPT_FAIL(149202, "加密失败"),

    DECRYPT_FAIL(149203, "解密失败"),

    PUBLIC_KEY_NOT_EXIST(149204, "公钥不存在"),

    KeyPairBo_EXPIRE(149205, "密钥对已过期"),

    RANDOM_EXPIRE(149300, "随机数已过期"),

    RANDOM_ERROR(149301, "随机数错误"),

    PASSWORD_VERIFY_ERROR(149302, "密码错误"),

    // ========== 其他模块 1002003000 ==========
    // ========== OAuth2 授权 1002022000 =========
    OAUTH2_CODE_NOT_EXISTS(1002022000, "code 不存在"),

    OAUTH2_CODE_EXPIRE(1002022000, "code 已过期"),

    ;

    private int code;
    private String msg;
}
