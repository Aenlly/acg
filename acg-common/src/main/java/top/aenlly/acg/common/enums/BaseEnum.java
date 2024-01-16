package top.aenlly.acg.common.enums;


import java.io.Serializable;

/**
 * 数据库插入值的枚举顶级接口
 * <p>
 * 所有枚举映射不能直接继承该接口
 * <p>
 * 应继承{@link StringBaseEnum}或{@link IntegerBaseEnum}
 * </p>
 *
 * <p>需要转换的枚举类都必须继承该接口
 *
 * @author Aenlly||tnw
 * @create 2022/10/12 14:02:42
 * @since 1.0.0
 */
public interface BaseEnum<T, D> extends Serializable {
    /**
     * 数据库字段值，前端请求字段
     */
    T getValue();


    /**
     * 说明值，前端显示备用
     */
    D getDesc();

}
