package top.aenlly.acg.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.aenlly.acg.bo.user.AppAuthLoginRespBO;
import top.aenlly.acg.bo.user.AppAuthPhoneLoginReqBO;
import top.aenlly.acg.bo.user.AppAuthPhoneSmsLoginReqBO;
import top.aenlly.acg.bo.user.AppAuthResetPasswordReqBO;

/**
 * 会员的认证 Service 接口
 *
 * @author anonymous
 */
@Service
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {

    String CLIENT_ID_DEFAULT = "default";

    @Override
    public void resetPassword(AppAuthResetPasswordReqBO userReqBO) {

    }

    @Override
    public void logout(String token) {

    }

    @Override
    public AppAuthLoginRespBO refreshToken(String refreshToken) {
        return null;
    }

    @Override
    public AppAuthLoginRespBO phoneLogin(AppAuthPhoneLoginReqBO reqBO) {
        return null;
    }

    @Override
    public AppAuthLoginRespBO phoneSmsLogin(AppAuthPhoneSmsLoginReqBO reqBO) {
        return null;
    }

    @Override
    public String getCard(String code, Long userId) {
        return null;
    }
}
