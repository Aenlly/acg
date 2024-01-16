package top.aenlly.acg.common.exception.code;

/**
 * @author Aenlly||tnw
 * @create 2024/01/15 17:20
 * @since 1.0.0
 */

public interface ErrorCode {
    /**
     * 获取错误码
     *
     * @return int
     */
    int getCode();

    /**
     * 获取错误信息
     *
     * @return int
     */
    String getMsg();
}
