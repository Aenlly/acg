package top.aenlly.acg.core.web.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import top.aenlly.acg.common.utils.servlet.ServletUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Request Body 缓存 Filter，实现它的可重复读取
 *
 * @author anonymous
 */
public class CacheRequestBodyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        filterChain.doFilter(new CacheRequestBodyWrapper(request), new CacheResponseBodyWrapper(response));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 只处理 json 请求内容
//        return !ServletUtils.isJsonRequest(request);
        return ServletUtils.isMultipartRequest(request);
    }

}
