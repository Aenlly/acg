package top.aenlly.acg.core.service;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import top.aenlly.acg.core.logger.ApiErrorLogApi;
import top.aenlly.acg.core.logger.dto.ApiErrorLogCreateReqDTO;

/**
 * API 错误日志 Framework Service 实现类
 * <p>
 * 基于 {@link ApiErrorLogApi} 服务，记录错误日志
 *
 * @author anonymous
 */
@RequiredArgsConstructor
public class ApiErrorLogFrameworkServiceImpl implements ApiErrorLogFrameworkService {

    private final ApiErrorLogApi apiErrorLogApi;

    @Override
    public void createApiErrorLog(ApiErrorLog apiErrorLog) {
        ApiErrorLogCreateReqDTO reqDTO = BeanUtil.copyProperties(apiErrorLog, ApiErrorLogCreateReqDTO.class);
        apiErrorLogApi.createApiErrorLog(reqDTO);
    }

}
