package top.aenlly.acg.common.enums;

/**
 * {@link Integer}转换枚举的顶层接口
 * <p>
 * 前端传{@link Integer}类型转换成枚举类时，需要继承该接口
 * <p>
 * 数据库存储为{@link Integer}类型
 * <p>需要转换的枚举类都必须继承该接口
 *
 * <p>
 * 前端必须传value值，而非desc值
 * </p>
 * </p>
 * 需要{@link T}指定返回值类型
 * <p>
 *
 * @author Aenlly||tnw
 * @create 2022/10/13 9:58
 * @since 1.0.0
 */
public interface IntegerBaseEnum<T> extends BaseEnum<Integer, T> {


}
