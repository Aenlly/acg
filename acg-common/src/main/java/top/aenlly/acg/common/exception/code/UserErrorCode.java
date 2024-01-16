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
public enum UserErrorCode implements ErrorCode {
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

    SYSTEM_ERROR(149004, "系统错误"),
    PARAM_ERROR(149005, "请求参数错误"),
    SPLICT_AMOUNT_NOT_ZERO(149006, "分账金额不能为0"),

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
    USER_PHONE_EXISTS(150202, "手机号已注册"),
    USER_NOT_EXISTS(150203, "用户不存在"),
    USER_IMPORT_LIST_IS_EMPTY(150204, "导入用户数据不能为空！"),
    USER_PASSWORD_FAILED(150205, "原密码不正确"),
    USER_IS_DISABLE(150206, "名字为【{}】的用户已被禁用"),
    NOT_ORIGIN_PHONE(150207, "不能修改为原手机号"),
    USER_COUNT_MAX(150208, "创建用户失败，原因：超过租户最大租户配额({})！"),
    PASSWORD_ENSURE_NOT_MATCH(150209, "密码确认与密码不匹配"),
    USER_REGISTER_COUNT_MAX(150210, "用户注册失败,请稍后重试"),
    USER_ACCOUNT_NOT_EXIST(150211, "用户账户不存在"),
    USER_NUMBER_COUNT_MAX(150212, "用户名num超标"),
    USER_FORGET_PWD_TOKEN_ERROR(150213, "忘记密码token已失效"),
    EMAIL_NOT_NULL(150202, "邮箱不能为空"),
    SMS_CODE_EXPIRE(150203, "短信验证码已失效"),
    CAPTCHA_CODE_EXPIRE(150204, "图形验证码已失效"),
    CAPTCHA_CODE_ERROR(150205, "图形验证码错误"),

    USER_EXIST_PROCESS_APPROVE(150303, "存在审批中与审批通过记录，无法修改身份证！"),

    ID_CARD_EXISTS(150304, "该身份证已注册"),
    ID_CARD_ERROR(150305, "身份证错误，请检查填写信息"),

    // ========= 文件相关 1001003000=================
    FILE_PATH_EXISTS(1001003000, "文件路径已存在"),
    FILE_NOT_EXISTS(1001003001, "文件不存在"),
    FILE_IS_EMPTY(1001003002, "文件为空"),
    FILE_IMAGE_NOT_SUPPORT(1001003003, "文件格式不支持"),
    FILE_IMAGE_SIZE_LIMIT(1001003004, "文件大小限制"),


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
    ;
    private int code;
    private String msg;

}
