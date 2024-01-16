package top.aenlly.acg.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 服务器异常 Exception
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class WechatCodeErrorException extends RuntimeException {
}
