package top.aenlly.acg.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 短信框架的错误码枚举
 * <p>
 * 短信框架，使用 2-001-000-000 段
 *
 * @author anonymous
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum SmsFrameworkErrorCode implements ErrorCode {

    SMS_UNKNOWN(2001000000, "未知错误，需要解析"),

    // ========== 权限 / 限流等相关 2001000100 ==========

    SMS_PERMISSION_DENY(2001000100, "没有发送短信的权限"),
    // 云片：可以配置 IP 白名单，只有在白名单中才可以发送短信
    SMS_IP_DENY(2001000100, "IP 不允许发送短信"),

    // 阿里云：将短信发送频率限制在正常的业务限流范围内。默认短信验证码：使用同一签名，对同一个手机号验证码，支持 1 条 / 分钟，5 条 / 小时，累计 10 条 / 天。
    SMS_SEND_BUSINESS_LIMIT_CONTROL(2001000102, "指定手机的发送限流"),
    // 阿里云：已经达到您在控制台设置的短信日发送量限额值。在国内消息设置 > 安全设置，修改发送总量阈值。
    SMS_SEND_DAY_LIMIT_CONTROL(2001000103, "每天的发送限流"),

    SMS_SEND_CONTENT_INVALID(2001000104, "短信内容有敏感词"),

    // 腾讯云：为避免骚扰用户，营销短信只允许在8点到22点发送。
    SMS_SEND_MARKET_LIMIT_CONTROL(2001000105, "营销短信发送时间限制"),

    // ========== 模板相关 2001000200 ==========
    SMS_TEMPLATE_INVALID(2001000200, "短信模板不合法"), // 包括短信模板不存在
    SMS_TEMPLATE_PARAM_ERROR(2001000201, "模板参数不正确"),

    // ========== 签名相关 2001000300 ==========
    SMS_SIGN_INVALID(2001000300, "短信签名不可用"),

    // ========== 账户相关 2001000400 ==========
    SMS_ACCOUNT_MONEY_NOT_ENOUGH(2001000400, "账户余额不足"),
    SMS_ACCOUNT_INVALID(2001000401, "apiKey 不存在"),

    // ========== 其它相关 2001000900 开头 ==========
    SMS_API_PARAM_ERROR(2001000900, "请求参数缺失"),
    SMS_MOBILE_INVALID(2001000901, "手机格式不正确"),
    SMS_MOBILE_BLACK(2001000902, "手机号在黑名单中"),
    SMS_APP_ID_INVALID(2001000903, "SdkAppId不合法"),

    EXCEPTION(2001000999, "调用异常"),

    // ========== 移动短信 2001000000 开头 ==========
    SMS_ILLEGAL_MAC(2001000000, "mac校验不通过。"),
    SMS_ILLEGAL_SIGN_ID(2001000001, "无效的签名编码。"),
    SMS_INVALID_MESSAGE(2001000002, "非法消息，请求数据解析失败"),
    SMS_INVALID_USR_OR_PWD(2001000002, "非法用户名/密码。"),
    SMS_NO_SIGN_ID(2001000003, "未匹配到对应的签名信息。"),
    SMS_TOO_MANY_MOBILES(2001000005, "手机号数量超限（>5000，，应≤5000。"),

    ;
    private int code;
    private String msg;
}
