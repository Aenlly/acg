package top.aenlly.acg.controller.auth.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import top.aenlly.acg.common.enums.SmsSceneEnum;
import top.aenlly.acg.common.validation.Mobile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("用户 APP - 发送手机验证码 Request VO")
@Data
@Accessors(chain = true)
public class AppAuthSmsSendReqVO {

    @ApiModelProperty(value = "手机号", required = true, example = "18274712831")
    @Mobile
    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "发送场景", example = "1", notes = "对应 UserRedisTypeEnum 枚举")
    @NotNull(message = "发送场景不能为空")
    private SmsSceneEnum scene;

    @ApiModelProperty(value = "验证码", required = true)
//    @NotNull(message = "验证码不能为空")
    private String captcha;

    @ApiModelProperty(value = "验证码key", required = true)
//    @NotNull(message = "验证码key不能为空")
    private String uuid;

}
