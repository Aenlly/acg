package top.aenlly.acg.core.security.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import top.aenlly.acg.core.security.core.annotations.PreAuthenticated;
import top.aenlly.acg.core.security.core.util.SecurityFrameworkUtils;

import static top.aenlly.acg.common.enums.GlobalErrorCode.UNAUTHORIZED;
import static top.aenlly.acg.common.exception.util.ServiceExceptionUtil.exception;

@Aspect
@Slf4j
public class PreAuthenticatedAspect {

    @Around("@annotation(preAuthenticated)")
    public Object around(ProceedingJoinPoint joinPoint, PreAuthenticated preAuthenticated) throws Throwable {
        if (SecurityFrameworkUtils.getLoginUser() == null) {
            throw exception(UNAUTHORIZED);
        }
        return joinPoint.proceed();
    }

}
