package top.aenlly.acg.common.validation;

import lombok.extern.slf4j.Slf4j;
import top.aenlly.acg.common.pojo.File;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author Aenlly||tnw
 * @create 2022/10/31 12:06
 * @since 1.0.0
 */
@Slf4j
public class FileCheckValidator implements ConstraintValidator<FileCheck, Object> {

    private FileCheck annotation;

    @Override
    public void initialize(FileCheck annotation) {
        this.annotation = annotation;
    }


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean aclCheck = true;
        if (value instanceof List) {
            List<?> list = (List<?>) value;
            if (list.size() > this.annotation.max()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(String.format("The size of file is %d which is greater than %d",
                                list.size(),
                                this.annotation.max()))
                        .addConstraintViolation();
            }
            for (Object f : list) {
                if (f instanceof File) {
                    File file = (File) f;
                    if (this.annotation.acl() != file.getAcl()) {
                        aclCheck = false;
                        break;
                    }
                }
            }
        } else if (value instanceof File) {
            File file = (File) value;
            if (this.annotation.acl() != file.getAcl()) {
                aclCheck = false;
            }
        }
        // 如果文件权限不匹配则报错
        if (!aclCheck) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("unmatched file acl")
                    .addConstraintViolation();
            return false;
        }
        // 非必要||list且不为空
        return (!this.annotation.must() || value instanceof File || (value instanceof List && ((List<?>) value).size() > 0));
    }
}
