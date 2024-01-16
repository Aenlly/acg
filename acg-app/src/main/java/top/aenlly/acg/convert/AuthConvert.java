package top.aenlly.acg.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.aenlly.acg.bo.user.AppAuthLoginRespBO;
import top.aenlly.acg.bo.user.AppAuthPhoneLoginReqBO;
import top.aenlly.acg.bo.user.AppAuthPhoneSmsLoginReqBO;
import top.aenlly.acg.bo.user.AppAuthResetPasswordReqBO;
import top.aenlly.acg.controller.auth.vo.login.AppAuthLoginRespVO;
import top.aenlly.acg.controller.auth.vo.login.AppAuthPhoneLoginReqVO;
import top.aenlly.acg.controller.auth.vo.login.AppAuthPhoneSmsLoginReqVO;
import top.aenlly.acg.controller.auth.vo.login.AppAuthResetPasswordReqVO;

/**
 * @author Aenlly||tnw
 * @create 2024/01/16 17:49
 * @since 1.0.0
 */
@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    AppAuthResetPasswordReqBO convert(AppAuthResetPasswordReqVO bean);

    AppAuthPhoneSmsLoginReqBO convert(AppAuthPhoneSmsLoginReqVO reqVO);

    AppAuthLoginRespVO convert(AppAuthLoginRespBO refreshToken);

    AppAuthPhoneLoginReqBO convert(AppAuthPhoneLoginReqVO reqVO);
}
