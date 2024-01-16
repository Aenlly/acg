package top.aenlly.acg.common.oauth2.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * OAuth2.0 访问令牌的校验 Response DTO
 *
 * @author anonymous
 */
@Data
public class OAuth2AccessTokenDTO implements Serializable {

    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 用户类型
     */
    private Integer userType;

}
