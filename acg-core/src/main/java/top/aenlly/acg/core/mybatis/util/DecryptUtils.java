package top.aenlly.acg.core.mybatis.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.extra.spring.SpringUtil;
import org.jasypt.encryption.StringEncryptor;

/**
 * 解密工具类
 *
 * @author Aenlly||tnw
 * @create 2022/11/21 17:23
 * @since 1.0.0
 */

public class DecryptUtils {
    private static StringEncryptor encryptor;

    static {
        encryptor = SpringUtil.getBean(StringEncryptor.class);
        Assert.notNull(encryptor, "StringEncryptor 不能为空");
    }

    public static String encrypt(String rawValue) {
        return encryptor.encrypt(rawValue);
    }
}
