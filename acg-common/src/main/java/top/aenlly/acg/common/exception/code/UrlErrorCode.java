package top.aenlly.acg.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 错误代码常量
 *
 * @author Aenlly||tnw
 * @create 2022/10/12 14:02:45
 * @since 1.0.0
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum UrlErrorCode implements ErrorCode {
    // TODO:错误码
    ID_NOT_FOUND(1105010001, "操作数据不存在"),
    DATA_URL_NULL(1105010002, "url或正文必须有一个数据存在"),
    UPDATE_ERROR(1105010003, "参数错误"),
    UPDATES_ERROR(1105010004, "修改失败"),
    ADD_ERROR(1105010005, "添加失败"),
    DELETE_ERROR(1105010006, "删除失败"),
    PERMISSIONS_ERROR(1105010007, "当前用户没有权限"),
    REFUSAL_TO_SUBMIT(00000005, "该用户未上传凭证"),

    // ========== 远程相关  ==========
    THE_THIRD_PARTY_INTERFACE_REQUEST_FAILED(151100, "第三方接口请求失败"),
    THE_THIRD_PARTY_RESPONSE_RESULT_IS_ABNORMAL(151101, "第三方响应结果异常"),
    ;
    private int code;
    private String msg;
}
