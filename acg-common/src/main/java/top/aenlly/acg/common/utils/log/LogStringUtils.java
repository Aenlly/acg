package top.aenlly.acg.common.utils.log;

import cn.hutool.core.util.StrUtil;

public class LogStringUtils {
    public static String shortBody(String body) {
        if (body != null) {
            return body.length() > 2500 ? StrUtil.sub(body, 0, 2499) : body;
        }
        return null;
    }

    public static String shortBody(String body, int length) {
        if (body != null) {
            return body.length() > length ? StrUtil.sub(body, 0, length - 1) : body;
        }
        return null;
    }
}
