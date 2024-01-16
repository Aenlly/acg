package top.aenlly.acg.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.aenlly.acg.common.enums.GlobalErrorCode;

import java.io.Serializable;

/**
 * @author Aenlly
 * @create by date 2021/11/07 14:13
 * @projectName RefuseClassificationCultivate
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "公共返回类", description = "公共返回类")
public class CommonResult<T> implements Serializable {
  private static final long serialVersionUID = 8178772928508874210L;
  /**
   * 状态码*
   */
  @ApiModelProperty(example = "返回状态")
  private Integer code;
  /**
   * 提示内容*
   */
  @ApiModelProperty(example = "返回信息")
  private String msg;
  /**
   * 返回内容*
   */
  @ApiModelProperty(example = "返回内容")
  private T data;

  /**
   * 错误时使用
   *
   * @param messageResultEnum 返回类型
   */
  public CommonResult(GlobalErrorCode messageResultEnum) {
    this.code = messageResultEnum.getCode();
    this.msg = messageResultEnum.getMsg();
  }

  /**
   * 正确时使用
   *
   * @param messageResultEnum 返回类型
   * @param data              返回内容
   */
  public CommonResult(GlobalErrorCode messageResultEnum, T data) {
    this.code = messageResultEnum.getCode();
    this.msg = messageResultEnum.getMsg();
    this.data = data;
  }

  public CommonResult(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }


  /**
   * 请求数据操作成功
   *
   * <p>统一返回内容执行方法
   *
   * @param t   返回内容
   * @param <T> 类型
   * @return 公共返回内容
   */
  public static <T> CommonResult<T> success(T t) {
    return new CommonResult<>(GlobalErrorCode.OK, t);
  }

  /**
   * 请求数据操作失败
   *
   * <p>统一返回内容方法执行方法
   *
   * @param <T> 推断类型，不需要
   * @return 公共错误返回内容
   */
  public static <T> CommonResult<T> resultError() {
    return new CommonResult<>(GlobalErrorCode.INTERNAL_SERVER_ERROR);
  }

  /**
   * 超出最大数量限制时
   *
   * <p>统一返回内容方法执行方法
   *
   * @param <T> 推断类型，不需要
   * @return 公共错误返回内容
   */
  public static <T> CommonResult<T> resultExceed() {
    return new CommonResult<>(GlobalErrorCode.EXCEED);
  }

  /**
   * 插入数据时，已存在内容
   *
   * <p>统一返回方法执行
   *
   * <p>统一返回内容方法执行方法
   *
   * @param <T> 推断类型，不需要
   * @return 公共错误返回内容
   */
  public static <T> CommonResult<T> resultExist() {
    return new CommonResult<>(GlobalErrorCode.EXIST);
  }

  public static CommonResult<String> resultPermission_Not() {
    return new CommonResult<>(GlobalErrorCode.PERMISSION_NOT);
  }

  /**
   * <p>统一返回方法执行
   *
   * <p>统一返回内容方法执行方法
   *
   * @param <T> 推断类型，不需要
   * @return 公共错误返回内容
   */
  public static <T> CommonResult<T> resultCustomize(GlobalErrorCode codeMsgResult) {
    return new CommonResult<>(codeMsgResult);
  }

  public static <T> CommonResult<T> error(int code, String msg) {
    return new CommonResult<>(code, msg);
  }

  public static <T> CommonResult<T> error(GlobalErrorCode codeMsgResult) {
    return new CommonResult<>(codeMsgResult);
  }

}
