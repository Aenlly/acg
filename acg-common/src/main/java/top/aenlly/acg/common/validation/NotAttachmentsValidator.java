package top.aenlly.acg.common.validation;

import lombok.extern.slf4j.Slf4j;
import top.aenlly.acg.common.pojo.File;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 验证
 *
 * @author Aenlly||tnw
 * @create 2022/10/24 17:49:57
 * @since 1.0.0
 */
@Slf4j
public class NotAttachmentsValidator implements ConstraintValidator<NotAttachments, Map<String, List<File>>> {

    private final static String REG = "\\{value}";

    private final Map<String, NotAttachment> map = new HashMap<>(8);

    @Override
    public void initialize(NotAttachments annotation) {
        NotAttachment[] values = annotation.value();
        for (NotAttachment value : values) {
            this.map.put(value.value(), value);
        }
    }

    @Override
    public boolean isValid(Map<String, List<File>> data, ConstraintValidatorContext context) {
        // 注解的信息
        Set<String> keys = this.map.keySet();

        // 如果传值信息大于注解信息，说明前端传过来的key不符合要求
        if (data.size() > keys.size()) {
            extracted(context, "附件参数不符合规范");
            return false;
        }

        // 遍历注解信息
        for (String key : keys) {
            NotAttachment notAttachment = this.map.get(key);

            boolean isMust = notAttachment.must();
            boolean isContain = data.containsKey(key);
            // 如果是非必输字段，传值为空，直接跳过校验
            if (!isMust && !isContain) {
                continue;
            }
            // 如果是 必输字段，传值为空，直接返回false
            if (isMust && !isContain) {
                String s = context.getDefaultConstraintMessageTemplate().replaceAll(REG, notAttachment.desc());
                extracted(context, s);
                return false;
            }

            List<File> files = data.get(key);
            // 校验文件最大数量
            if (files.size() > notAttachment.max()) {
                String format = String.format("附件[%s]超过最大数量[%s],传入[%s]", notAttachment.desc(), notAttachment.max(), files.size());
                extracted(context, format);
                return false;
            }
            // 校验文件最小数量
            // 如果是非必填字段 不需要校验文件可能传空数组的情况
            if (files.size() <= 0 && isMust) {
                String format = String.format("附件为空[%s]", notAttachment.desc());
                extracted(context, format);
                return false;
            }
            // 校验文件权限
            for (File f : files) {
                // 判断前端传过来的附件和权限和自己设置的权限是否一致如果不一致报错
                if (f.getAcl() != notAttachment.acl()) {
                    String format = String.format("unmatched key[%s] acl", key);
                    extracted(context, format);
                    return false;
                }
            }
        }
        // 校验通过
        return true;
    }

    private void extracted(ConstraintValidatorContext context, String str) {
        // 校验不通过，自定义提示语句（因为，注解上的 value 是枚举类，无法获得枚举类的实际值）
        context.disableDefaultConstraintViolation(); // 禁用默认的 message 的值
        context.buildConstraintViolationWithTemplate(str).addConstraintViolation(); // 重新添加错误提示语句
    }

}

