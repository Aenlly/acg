package top.aenlly.acg.common.enums;

/**
 * {@link String}转换枚举的顶层接口
 * <p>
 * 前端传{@link String}类型转换成枚举类时，需要继承该接口
 * <p>
 * 数据库存储为{@link String}类型
 * <p>需要转换的枚举类都必须继承该接口
 * <p>
 * 前端必须传value值，而非desc值
 * </p>
 * 需要{@link T}指定返回值类型
 * <p>
 *
 * @author Aenlly||tnw
 * @create 2022/10/21 15:42:48
 * @since 1.0.0
 */

public interface StringBaseEnum<T> extends BaseEnum<String, T> {

}
