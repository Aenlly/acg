package top.aenlly.acg.bo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import top.aenlly.acg.common.pojo.VerifyBaseVo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * 应用程序身份验证电子邮件登录要求 vo
 *
 * @author Aenlly||tnw
 * @create 2024/01/16 17:39:46
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户 APP - 邮箱 + 密码登录 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthEmailLoginReqBO extends VerifyBaseVo {

    @ApiModelProperty(value = "邮箱", required = true, example = "1073920692@qq.com")
    @NotNull(message = "Please enter a valid email address")
    @Email(message = "Incorrect mailbox format")
    private String email;

    @ApiModelProperty(value = "密码", required = true, example = "admin123")
    @NotNull(message = "Password cannot be empty")
    private String password;
}
