package top.aenlly.acg.controller.auth.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.aenlly.acg.common.pojo.VerifyBaseVo;
import top.aenlly.acg.common.validation.Mobile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("手机+密码登录 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthPhoneLoginReqVO extends VerifyBaseVo {

    @ApiModelProperty(value = "手机号", required = true, example = "18274712831")
    @NotEmpty(message = "手机号不能为空")
    @Mobile
    private String phone;

    @ApiModelProperty(value = "验证码", required = true)
    @NotNull(message = "验证码不能为空")
    private String captcha;

    @ApiModelProperty(value = "验证码key", required = true)
    @NotNull(message = "验证码key")
    private String uuid;

    @ApiModelProperty(value = "uuidKey")
    private String uuidKey;

}
