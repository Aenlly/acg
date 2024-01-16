package top.aenlly.acg.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.aenlly.acg.common.exception.code.ErrorCode;

/**
 * 返回状态码
 *
 * @author Aenlly
 * @create by date 2021/12/11 15:58
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum GlobalErrorCode implements ErrorCode {
  /**
   * 成功
   */
  OK(200, "请求成功！"),
  EXCEED(-1, "超出最大数量"),
  /**
   * 已存在
   */
  EXIST(300, "数据已存在"),
  BAD_REQUEST(400, "请求参数不正确"),
  UNAUTHORIZED(401, "账号未登录"),
  /**
   * 权限不足
   */
  PERMISSION_NOT(402, "登录过期"),
  FORBIDDEN(403, "没有该操作权限"),

  NOT_FOUND(404, "请求未找到"),
  METHOD_NOT_ALLOWED(405, "请求方法不正确"),
  /**
   * 并发请求，不允许
   */
  LOCKED(423, "请求失败，请稍后重试"),
  TOO_MANY_REQUESTS(429, "请求过于频繁，请稍后重试"),
  // ========== 服务端错误段 ==========
  INTERNAL_SERVER_ERROR(500, "系统异常"),

  DEMO_DENY(901, "演示模式，禁止写操作"),
  /**
   * 重复请求
   */
  REPEATED_REQUESTS(900, "重复请求，请稍后重试"),
  UNKNOWN(999, "未知错误"),

  ;

  private int code;

  private String msg;
}
