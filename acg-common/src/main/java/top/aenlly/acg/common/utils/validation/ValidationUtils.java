package top.aenlly.acg.common.utils.validation;

import cn.hutool.core.collection.CollUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 校验工具类
 *
 * @author anonymous
 */
public class ValidationUtils {
    /*
     *
     * 说起正则表达式，手机号的正则算是最常用的。常见的手机号正则有 /^1[3456789]\d{9}$/、/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/。
     *
     * 手机号这东西更新速度还是比较快的，所以不能限制的太死。我这里只作前三位号段的限制。
     *
     * 根据我的一番调查和测试，得出如下号段：
     *
     * 运营商	号段
     * 电信	133,149,153,173,174,177,180,181,189,191,193,199
     * 移动	134,135,136,137,138,139,147,148,150,151,152,157,158,159,172,178,182,183,184,187,188,195,198
     * 联通	130,131,132,145,146,155,156,166,175,176,185,186,196
     * 广电	190,192,197
     * 电信虚拟	162,1700,1701,1702
     * 移动虚拟	165,1703,1705,1706
     * 联通虚拟	167,1704,1707,1708,1709,171
     *
     * /^1(3\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\d|9[0-35-9])\d{8}$/
     *
     * ————————————————
     * 原文作者：Violet_Ice紫冰
     * 转自链接：https://learnku.com/articles/31543
     */
    // private static final Pattern PATTERN_MOBILE = Pattern.compile("^1(3\\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$");

    /*
     * 这个是原始的号段校验 有问题！！！
     */
    // private static final Pattern PATTERN_MOBILE = Pattern.compile("^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[5-79])|(?:5[0-35-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[189]))\\d{8}$");

    /**
     * 考虑到号段更新的比较快还是使用简单的号段校验规则
     * from : https://blog.csdn.net/weixin_42216142/article/details/100533087
     */
    private static final Pattern PATTERN_MOBILE = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");

    private static final Pattern PATTERN_URL = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    private static final Pattern PATTERN_XML_NCNAME = Pattern.compile("[a-zA-Z_][\\-_.0-9_a-zA-Z$]*");

    public static boolean isMobile(String mobile) {
        return StringUtils.hasText(mobile)
                && PATTERN_MOBILE.matcher(mobile).matches();
    }

    public static boolean isURL(String url) {
        return StringUtils.hasText(url)
                && PATTERN_URL.matcher(url).matches();
    }

    public static boolean isXmlNCName(String str) {
        return StringUtils.hasText(str)
                && PATTERN_XML_NCNAME.matcher(str).matches();
    }

    public static void validate(Validator validator, Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (CollUtil.isNotEmpty(constraintViolations)) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

}
