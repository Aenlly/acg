package top.aenlly.acg.controller.auth;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.anji.captcha.controller.CaptchaController.getRemoteId;
import static top.aenlly.acg.common.exception.code.UserErrorCode.CAPTCHA_CODE_ERROR;
import static top.aenlly.acg.common.exception.util.ServiceExceptionUtil.exception;

@Api(tags = "图新验证码验证码")
@RestController
// @RequestMapping("/captcha") 使用默认的CaptchaController
public class AjCaptchaController {

    @Resource
    private CaptchaService captchaService;

    @PostMapping("/get")
    public ResponseModel get(@RequestBody CaptchaVO data, HttpServletRequest request) {
        assert request.getRemoteHost() != null;
        data.setBrowserInfo(getRemoteId(request));
        return captchaService.get(data);
    }

    @PostMapping("/check")
    public ResponseModel check(@RequestBody CaptchaVO data, HttpServletRequest request) {
        data.setBrowserInfo(getRemoteId(request));
        try {
            return captchaService.check(data);
        } catch (Exception e) {
            // 这里可能会报错空指针验证失败，暂时忽略报错
            //  java.lang.NullPointerException
            //	at com.anji.captcha.service.impl.BlockPuzzleCaptchaServiceImpl.check(BlockPuzzleCaptchaServiceImpl.java:110)
            throw exception(CAPTCHA_CODE_ERROR);
        }
    }
}
