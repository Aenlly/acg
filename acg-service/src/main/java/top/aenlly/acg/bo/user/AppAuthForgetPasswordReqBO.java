package top.aenlly.acg.bo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.aenlly.acg.common.pojo.VerifyBaseVo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * App Auth 忘记密码 req vo
 *
 * @author Aenlly||tnw
 * @create 2024/01/16 17:39:39
 * @since 1.0.0
 */
@ApiModel("忘记密码vo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthForgetPasswordReqBO extends VerifyBaseVo {

    @ApiModelProperty(value = "邮箱", required = true, example = "1073920692@qq.com")
    // @NotNull(message = "Please enter a valid email address")
    @Email(message = "Incorrect mailbox format")
    private String email;

    @ApiModelProperty(value = "密码", required = true, example = "admin123")
    @NotNull(message = "Password cannot be empty")
    private String password;

    @ApiModelProperty(value = "用户id", required = true, example = "1")
    @NotNull(message = "user id cannot be empty")
    private String userId;

    @ApiModelProperty(value = "token", required = true, example = "123456")
    @NotEmpty(message = "Verification code cannot be empty")
    private String token;

}
