package top.aenlly.acg.config.log.filter;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import top.aenlly.acg.common.enums.GlobalErrorCode;
import top.aenlly.acg.common.pojo.CommonResult;
import top.aenlly.acg.common.utils.date.DateUtils;
import top.aenlly.acg.common.utils.log.LogStringUtils;
import top.aenlly.acg.common.utils.servlet.ServletUtils;
import top.aenlly.acg.common.utils.tracer.TracerUtils;
import top.aenlly.acg.core.properties.web.WebProperties;
import top.aenlly.acg.core.service.ApiAccessLog;
import top.aenlly.acg.core.service.ApiAccessLogFrameworkService;
import top.aenlly.acg.core.web.filter.ApiRequestFilter;
import top.aenlly.acg.core.web.filter.CacheResponseBodyWrapper;
import top.aenlly.acg.core.web.utils.WebFrameworkUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static top.aenlly.acg.common.utils.json.JsonUtils.toJsonString;

/**
 * API 访问日志 Filter
 *
 * @author anonymous
 */
@Slf4j
public class ApiAccessLogFilter extends ApiRequestFilter {

    private final String applicationName;

    private final ApiAccessLogFrameworkService apiAccessLogFrameworkService;

    public ApiAccessLogFilter(WebProperties webProperties,
            String applicationName,
            ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        super(webProperties);
        this.applicationName = applicationName;
        this.apiAccessLogFrameworkService = apiAccessLogFrameworkService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 获得开始时间
        Date beginTim = new Date();
        // 提前获得参数，避免 XssFilter 过滤处理
        Map<String, String> queryString = ServletUtil.getParamMap(request);
        String requestBody = ServletUtils.isJsonRequest(request) ? ServletUtil.getBody(request) : null;

        try {
            // 继续过滤器
            filterChain.doFilter(request, response);
            // 正常执行，记录日志
            createApiAccessLog(request, response, beginTim, queryString, requestBody, null);
        } catch (Exception ex) {
            // 异常执行，记录日志
            createApiAccessLog(request, response, beginTim, queryString, requestBody, ex);
        }
    }

    private void createApiAccessLog(HttpServletRequest request,
            HttpServletResponse response,
            Date beginTime,
            Map<String, String> queryString,
            String requestBody,
            Exception ex) {
        ApiAccessLog accessLog = new ApiAccessLog();
        try {
            this.buildApiAccessLogDTO(accessLog, request, response, beginTime, queryString, requestBody, ex);
            apiAccessLogFrameworkService.createApiAccessLog(accessLog);
        } catch (Throwable th) {
            log.error("[createApiAccessLog][url({}) log({}) 发生异常]", request.getRequestURI(), toJsonString(accessLog), th);
        }
    }

    private void buildApiAccessLogDTO(ApiAccessLog accessLog,
            HttpServletRequest request,
            HttpServletResponse response,
            Date beginTime,
            Map<String, String> queryString, String requestBody, Exception ex) {
        // 处理用户信息
        accessLog.setUserId(WebFrameworkUtils.getLoginUserId(request));
        accessLog.setUserType(WebFrameworkUtils.getLoginUserType(request));
        // 设置访问结果
        CommonResult<?> result = WebFrameworkUtils.getCommonResult(request);
        if (result != null) {
            accessLog.setResultCode(result.getCode());
            accessLog.setResultMsg(result.getMsg());
        } else if (ex != null) {
            accessLog.setResultCode(GlobalErrorCode.INTERNAL_SERVER_ERROR.getCode());
            accessLog.setResultMsg(ExceptionUtil.getRootCauseMessage(ex));
        } else {
            accessLog.setResultCode(0);
            accessLog.setResultMsg("");
        }
        // 设置其它字段
        accessLog.setTraceId(TracerUtils.getTraceId());
        accessLog.setApplicationName(applicationName);
        accessLog.setRequestUrl(request.getRequestURI());
        Map<String, Object> requestParams = MapUtil.<String, Object>builder().put("query", queryString).put("body", requestBody).build();
        accessLog.setRequestParams(toJsonString(requestParams));

        // response 数据为一次读取流，只能读取 com.hisun.rencai.framework.web.core.filter.CacheResponseBodyWrapper
        // 修饰过后的
        if (response instanceof CacheResponseBodyWrapper) {
            // 同时判断返回数据是json
            if (MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(response.getContentType())) {
                CacheResponseBodyWrapper cacheResponseBodyWrapper = (CacheResponseBodyWrapper) response;
                byte[] bytes = cacheResponseBodyWrapper.toByteArray();
                String responseStr = new String(bytes);
                accessLog.setResponseBody(LogStringUtils.shortBody(responseStr));
            }
        }

        accessLog.setRequestMethod(request.getMethod());
        accessLog.setUserAgent(ServletUtils.getUserAgent(request));
        accessLog.setUserIp(ServletUtil.getClientIP(request));
        // 持续时间
        accessLog.setBeginTime(beginTime);
        accessLog.setEndTime(new Date());
        accessLog.setDuration((int) DateUtils.diff(accessLog.getEndTime(), accessLog.getBeginTime()));
    }

}
