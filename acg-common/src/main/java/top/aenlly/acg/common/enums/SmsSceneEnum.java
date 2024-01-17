package top.aenlly.acg.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户短信验证码发送场景的枚举
 *
 * @author anonymous
 */
@Getter
@AllArgsConstructor
public enum SmsSceneEnum implements StringBaseEnum<String> {

    /*
     * 用户端
     */

    // 未登录场景
    USER_REGISTER("USER_REGISTER", "user-sms-register", "会员用户 - 注册"),
    USER_LOGIN("USER_LOGIN", "user-sms-login", "会员用户 - 手机号登陆"),
    USER_FORGET_PASSWORD("USER_FORGET_PASSWORD", "user-sms-forget-passwd", "会员用户 - 忘记密码"),

    USER_IMPORT_SUBSIDY("USER_IMPORT_SUBSIDY", "user-sms-import-subsidy", "会员用户 - 存量数据导入"),

//    USER_RESET_PASSWORD("USER_RESET_PASSWORD", "user-sms-reset-passwd", "会员用户 - 重置密码"), //暂时无此功能

    // 已登录场景
    USER_RESET_PHONE("USER_RESET_PHONE", "user-sms-reset-phone", "会员用户 - 修改手机"),
    USER_UNBOUND("USER_UNBOUND", "user-sms-unbind", "解除账号绑定"),


    /*
     * 管理后台端
     */
    // 未登录场景
    ADMIN_USER_LOGIN("ADMIN_USER_LOGIN", "admin-sms-login", "后台用户 - 手机号登录"),
    ADMIN_FORGET_PASSWORD("ADMIN_FORGET_PASSWORD", "admin-sms-forget-password", "后台用户 - 忘记密码"),

    ;

    @EnumValue
    @JsonValue
    private final String value;

    private final String templateCode;

    private final String desc;

}
