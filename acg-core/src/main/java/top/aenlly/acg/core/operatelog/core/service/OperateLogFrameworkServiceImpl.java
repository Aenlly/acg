package top.aenlly.acg.core.operatelog.core.service;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import top.aenlly.acg.core.operatelog.core.api.OperateLogApi;
import top.aenlly.acg.core.operatelog.core.api.dto.OperateLogCreateReqDTO;

/**
 * 操作日志 Framework Service 实现类
 * <p>
 * 基于 {@link OperateLogApi} 实现，记录操作日志
 *
 * @author anonymous
 */
@RequiredArgsConstructor
public class OperateLogFrameworkServiceImpl implements OperateLogFrameworkService {

    private final OperateLogApi operateLogApi;

    @Override
    public void createOperateLog(OperateLog operateLog) {
        OperateLogCreateReqDTO reqDTO = BeanUtil.copyProperties(operateLog, OperateLogCreateReqDTO.class);
        operateLogApi.createOperateLog(reqDTO);
    }

}
