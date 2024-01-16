package top.aenlly.acg.service.user;


import top.aenlly.acg.bo.user.AppAuthLoginRespBO;
import top.aenlly.acg.bo.user.AppAuthPhoneLoginReqBO;
import top.aenlly.acg.bo.user.AppAuthPhoneSmsLoginReqBO;
import top.aenlly.acg.bo.user.AppAuthResetPasswordReqBO;

/**
 * 会员的认证 Service 接口
 * <p>
 * 提供用户的账号密码登录、token 的校验等认证相关的功能
 *
 * @author anonymous
 */
public interface UserAuthService {

    /**
     * 忘记密码
     */
    void resetPassword(AppAuthResetPasswordReqBO userReqBO);

    /**
     * 基于 token 退出登录
     */
    void logout(String token);

    /**
     * 刷新访问令牌
     */
    AppAuthLoginRespBO refreshToken(String refreshToken);


    /**
     * 手机号+密码登录
     */
    AppAuthLoginRespBO phoneLogin(AppAuthPhoneLoginReqBO reqBO);

    /**
     * 手机验证码登录
     */
    AppAuthLoginRespBO phoneSmsLogin(AppAuthPhoneSmsLoginReqBO reqBO);

    /**
     * 获取人才卡详情
     */
    String getCard(String code, Long userId);
}
