package top.aenlly.acg.common.pojo;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import top.aenlly.acg.common.enums.StringBaseEnum;

/**
 * 指定文件的访问权限ACL。
 * <p>
 * 需要同步更改 {@link com.hisun.rencai.module.file.dal.dataobject.enums.ACL}
 * </p>
 * <p>
 * 详情：
 *
 * @see <a href="https://docs.aws.amazon.com/AmazonS3/latest/userguide/acl-overview.html#canned-acl">ACL</a>
 * <p>
 * 有效值：
 * <p>
 * Ø  public-read-write：公共读写
 * <p>
 * Ø  public-read：公共读
 * <p>
 * Ø  private：私有（默认值）
 * <p>
 * authenticated-read：认证用户可读
 * <p>
 * bucket-owner-full-control
 */
@Getter
@AllArgsConstructor
public enum ACL implements StringBaseEnum<String> {

    /**
     * 目前应该只需要这两个权限
     */
    PUBLIC_READ("public-read", "公共读"),
    PRIVATE("private", "私有");

    @JsonValue
    private final String value;

    private final String desc;
}
