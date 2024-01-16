package top.aenlly.acg.core.web.filter;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import top.aenlly.acg.core.web.utils.WebFrameworkUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * web 请求logger日志打印 过滤器
 *
 * @author toi
 */
@AllArgsConstructor
public class LoggerFilter extends OncePerRequestFilter {

    protected static Logger logger = LoggerFactory.getLogger(LoggerFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        Long loginUserId = WebFrameworkUtils.getLoginUserId(request);
        logger.info("[u:{}][{}]{}", loginUserId == null ? "-" : loginUserId, method, uri);
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        // 排除 /admin/ 接口请求
        if (StrUtil.startWith(uri, "/admin/")) {
            return true;
        }
        if (StrUtil.endWithAnyIgnoreCase(uri, ".html", ".js", ".css")) {
            return true;
        }
        // 排除 html 请求
        return false;
    }

}
