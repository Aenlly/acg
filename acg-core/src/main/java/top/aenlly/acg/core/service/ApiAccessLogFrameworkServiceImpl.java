package top.aenlly.acg.core.service;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import top.aenlly.acg.core.logger.ApiAccessLogApi;
import top.aenlly.acg.core.logger.dto.ApiAccessLogCreateReqDTO;

/**
 * API 访问日志 Framework Service 实现类
 * <p>
 * 基于 {@link ApiAccessLogApi} 服务，记录访问日志
 *
 * @author anonymous
 */
@RequiredArgsConstructor
public class ApiAccessLogFrameworkServiceImpl implements ApiAccessLogFrameworkService {

    private final ApiAccessLogApi apiAccessLogApi;

    @Override
    public void createApiAccessLog(ApiAccessLog apiAccessLog) {
        ApiAccessLogCreateReqDTO reqDTO = BeanUtil.copyProperties(apiAccessLog, ApiAccessLogCreateReqDTO.class);
        apiAccessLogApi.createApiAccessLog(reqDTO);
    }

}
