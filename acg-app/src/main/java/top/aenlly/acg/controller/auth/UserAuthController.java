package top.aenlly.acg.controller.auth;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.aenlly.acg.common.pojo.CommonResult;
import top.aenlly.acg.controller.auth.vo.login.AppAuthLoginRespVO;
import top.aenlly.acg.controller.auth.vo.login.AppAuthPhoneLoginReqVO;
import top.aenlly.acg.controller.auth.vo.login.AppAuthPhoneSmsLoginReqVO;
import top.aenlly.acg.controller.auth.vo.login.AppAuthResetPasswordReqVO;
import top.aenlly.acg.convert.AuthConvert;
import top.aenlly.acg.core.properties.security.SecurityProperties;
import top.aenlly.acg.core.security.core.util.SecurityFrameworkUtils;
import top.aenlly.acg.service.user.UserAuthService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import static top.aenlly.acg.common.pojo.CommonResult.success;
import static top.aenlly.acg.core.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Api(tags = "A3-用户登录注册")
@RestController
@RequestMapping("/user/auth")
@Validated
@Slf4j
public class UserAuthController {

    @Resource
    private UserAuthService userAuthService;
    @Resource
    private SecurityProperties securityProperties;

    @PostMapping("/phone-login")
    @ApiOperation("b.用户登录  使用手机号 + 密码登录")
    public CommonResult<AppAuthLoginRespVO> phoneLogin(@RequestBody @Valid AppAuthPhoneLoginReqVO reqVO) {
        AppAuthLoginRespVO respVO = AuthConvert.INSTANCE.convert(userAuthService.phoneLogin(AuthConvert.INSTANCE.convert(reqVO)));
        return success(respVO);
    }

    @PostMapping("/phone-sms-login")
    @ApiOperation("b.用户登录  使用手机验证码登录")
    public CommonResult<AppAuthLoginRespVO> phoneSmsLogin(@RequestBody @Valid AppAuthPhoneSmsLoginReqVO reqVO) {
        AppAuthLoginRespVO respVO = AuthConvert.INSTANCE.convert(userAuthService.phoneSmsLogin(AuthConvert.INSTANCE.convert(reqVO)));
        return success(respVO);
    }

    @PostMapping("/resetPassword")
    @ApiOperation(value = "c.重置密码", notes = "用户忘记密码时使用")
    public CommonResult<Boolean> resetPassword(@RequestBody @Valid AppAuthResetPasswordReqVO reqVO) {
        userAuthService.resetPassword(AuthConvert.INSTANCE.convert(reqVO));
        return success(true);
    }

    @PostMapping("/logout")
    @ApiOperation("e.登出系统")
    public CommonResult<Boolean> logout(HttpServletRequest request) {
        String token = SecurityFrameworkUtils.obtainAuthorization(request, securityProperties.getTokenHeader());
        if (StrUtil.isNotBlank(token)) {
            userAuthService.logout(token);
        }
        return success(true);
    }

    @PostMapping("/refresh-token")
    @ApiOperation("f.刷新令牌")
    @ApiImplicitParam(name = "refreshToken", value = "刷新令牌", required = true, dataTypeClass = String.class)
    public CommonResult<AppAuthLoginRespVO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return success(AuthConvert.INSTANCE.convert(userAuthService.refreshToken(refreshToken)));
    }

    @GetMapping("/get-card")
    @ApiOperation("获取银行卡")
    public CommonResult<String> getCard(@Valid @RequestParam("code") @NotEmpty(message = "验证码不能为空") String code) {
        return success(userAuthService.getCard(code, getLoginUserId()));
    }

}
