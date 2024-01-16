package top.aenlly.acg.controller.auth.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * APP认证登录resp vo
 *
 * @author Aenlly||tnw
 * @create 2024/01/16 17:39:49
 * @since 1.0.0
 */
@ApiModel("用户 APP - 登录 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppAuthLoginRespVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "1024")
    private Long userId;

    @ApiModelProperty(value = "访问令牌", required = true, example = "happy")
    private String accessToken;

    @ApiModelProperty(value = "刷新令牌", required = true, example = "nice")
    private String refreshToken;

    @ApiModelProperty(value = "过期时间", required = true)
    private Date expiresTime;

    /**
     * lastName 姓  姓：选填    名：必填
     */
    @ApiModelProperty(value = "姓", required = true, example = "史蒂夫")
    private String lastName;
    /**
     * firstName 名
     */
    @ApiModelProperty(value = "名", required = true, example = "乔布斯")
    private String firstName;

}
