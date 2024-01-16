package top.aenlly.acg.core.logger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.aenlly.acg.core.logger.dto.ApiErrorLogCreateReqDTO;

import javax.validation.Valid;

/**
 * API 错误日志的 API 接口
 *
 * @author anonymous
 */
public interface ApiErrorLogApi {

    Logger logger = LoggerFactory.getLogger("errorPrinterLogger");

    /**
     * 创建 API 错误日志
     *
     * @param createDTO 创建信息
     */
    void createApiErrorLog(@Valid ApiErrorLogCreateReqDTO createDTO);

}
