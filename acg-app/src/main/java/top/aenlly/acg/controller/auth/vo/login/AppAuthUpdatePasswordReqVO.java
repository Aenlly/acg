package top.aenlly.acg.controller.auth.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.aenlly.acg.common.pojo.VerifyBaseVo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("用户 APP - 修改密码 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthUpdatePasswordReqVO extends VerifyBaseVo {

    @ApiModelProperty(value = "用户旧密码", required = true, example = "123456")
    @NotBlank(message = "Old password cannot be empty")
    private String oldPassword;

    @ApiModelProperty(value = "密码", required = true, example = "buzhidao")
    @NotNull(message = "Password cannot be empty")
    private String password;

    // 不使用校验密码 ，前端判断确认密码相等后再去加密
//    @ApiModelProperty(value = "确认密码", required = true)
//    private String ensurePwd;
}
