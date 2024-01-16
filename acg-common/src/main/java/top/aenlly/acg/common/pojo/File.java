package top.aenlly.acg.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.aenlly.acg.common.validation.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * <p style="#00ff00">
 * 如果是{@link Map <String,java.util.List<File>>}、{@link List <File>}类型存储到数据库的json，
 * <br />
 * 那么需要使用{@link JacksonFileTypeHandler}转换类,
 * 使用方法与{@link JacksonTypeHandler}一致
 * </p>
 * 附件公共序列化类
 *
 * @author Aenlly
 */
@Data
@NoArgsConstructor
public class File {
    @NotNull(message = "编号不能为空")
    private Long id;
    @NotEmpty(message = "文件名不能为空")
    private String name;
    @NotEmpty(message = "url不能为空")
    @URL(message = "链接格式错误")
    private String url;
    /**
     * 是否验证
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private ACL acl;
}
