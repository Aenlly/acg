package top.aenlly.acg.core.web.filter;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import top.aenlly.acg.core.properties.web.WebProperties;

import javax.servlet.http.HttpServletRequest;

/**
 * 过滤
 * /admin-api、
 * /app-api、
 * /ent-api
 * 等 API 请求的过滤器
 *
 * @author anonymous
 */
@RequiredArgsConstructor
public abstract class ApiRequestFilter extends OncePerRequestFilter {

    protected final WebProperties webProperties;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 只过滤 API 请求的地址
        return !StrUtil.startWithAny(
                request.getRequestURI(),
                webProperties.getAdminApi().getPrefix(),
                webProperties.getAppApi().getPrefix()
        );
    }

}
