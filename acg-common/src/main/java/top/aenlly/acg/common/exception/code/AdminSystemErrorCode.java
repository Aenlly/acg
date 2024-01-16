package top.aenlly.acg.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * System 错误码枚举类
 * <p>
 * system 系统，使用 1-002-000-000 段
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum AdminSystemErrorCode implements ErrorCode {

    // ========== AUTH 模块 1002000000 ==========
    AUTH_LOGIN_BAD_CREDENTIALS(1002000000, "登录失败，账号密码不正确"),

    AUTH_LOGIN_USER_DISABLED(1002000001, "登录失败，账号被禁用"),

    AUTH_LOGIN_CAPTCHA_CODE_ERROR(1002000004, "验证码不正确，原因：{}"),

    AUTH_THIRD_LOGIN_NOT_BIND(1002000005, "未绑定账号，需要进行绑定"),

    AUTH_TOKEN_EXPIRED(1002000006, "Token 已经过期"),

    AUTH_MOBILE_NOT_EXISTS(1002000007, "手机号不存在"),

    // ========== 菜单模块 1002001000 ==========
    MENU_NAME_DUPLICATE(1002001000, "已经存在该名字的菜单"),

    MENU_PARENT_NOT_EXISTS(1002001001, "父菜单不存在"),

    MENU_PARENT_ERROR(1002001002, "不能设置自己为父菜单"),

    MENU_NOT_EXISTS(1002001003, "菜单不存在"),

    MENU_EXISTS_CHILDREN(1002001004, "存在子菜单，无法删除"),

    MENU_PARENT_NOT_DIR_OR_MENU(1002001005, "父菜单的类型必须是目录或者菜单"),

    // ========== 角色模块 1002002000 ==========
    ROLE_NOT_EXISTS(1002002000, "角色不存在"),

    ROLE_NAME_DUPLICATE(1002002001, "已经存在名为【{}】的角色"),

    ROLE_CODE_DUPLICATE(1002002002, "已经存在编码为【{}】的角色"),

    ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE(1002002003, "不能操作类型为系统内置的角色"),

    ROLE_IS_DISABLE(1002002004, "名字为【{}】的角色已被禁用"),

    ROLE_ADMIN_CODE_ERROR(1002002005, "编码【{}】不能使用"),

    // ========== 用户模块 1002003000 ==========
    USER_USERNAME_EXISTS(1002003000, "用户账号已经存在"),

    USER_MOBILE_EXISTS(1002003001, "手机号已经存在"),

    USER_EMAIL_EXISTS(1002003002, "邮箱已经存在"),

    USER_NOT_EXISTS(1002003003, "用户不存在"),

    USER_IMPORT_LIST_IS_EMPTY(1002003004, "导入用户数据不能为空！"),

    USER_PASSWORD_FAILED(1002003005, "用户密码校验失败"),

    USER_IS_DISABLE(1002003006, "名字为【{}】的用户已被禁用"),

    USER_PASSWD_EASY(1002003007, "密码强度过于简单"),

    // ========== 部门模块 1002004000 ==========
    DEPT_NAME_DUPLICATE(1002004000, "已经存在该名字的部门"),

    DEPT_PARENT_NOT_EXITS(1002004001, "父级部门不存在"),

    DEPT_NOT_FOUND(1002004002, "当前部门不存在"),

    DEPT_EXITS_CHILDREN(1002004003, "存在子部门，无法删除"),

    DEPT_PARENT_ERROR(1002004004, "不能设置自己为父部门"),

    DEPT_EXISTS_USER(1002004005, "部门中存在员工，无法删除"),

    DEPT_NOT_ENABLE(1002004006, "部门不处于开启状态，不允许选择"),

    DEPT_PARENT_IS_CHILD(1002004007, "不能设置自己的子部门为父部门"),

    // ========== 岗位模块 1002005000 ==========
    POST_NOT_FOUND(1002005000, "当前岗位不存在"),

    POST_NOT_ENABLE(1002005001, "岗位({}) 不处于开启状态，不允许选择"),

    POST_NAME_DUPLICATE(1002005002, "已经存在该名字的岗位"),

    POST_CODE_DUPLICATE(1002005003, "已经存在该标识的岗位"),

    // ========== 通知公告 1002008000 ==========
    NOTICE_NOT_FOUND(1002008001, "当前通知公告不存在"),

    // ========== 短信渠道 1002011000 ==========
    SMS_CHANNEL_NOT_EXISTS(1002011000, "短信渠道不存在"),

    SMS_CHANNEL_DISABLE(1002011001, "短信渠道不处于开启状态，不允许选择"),

    SMS_CHANNEL_HAS_CHILDREN(1002011002, "无法删除，该短信渠道还有短信模板"),

    // ========== 短信模板 1002012000 ==========
    SMS_TEMPLATE_NOT_EXISTS(1002012000, "短信模板不存在"),

    SMS_TEMPLATE_CODE_DUPLICATE(1002012001, "已经存在编码为【{}】的短信模板"),

    // ========== 短信发送 1002013000 ==========
    SMS_SEND_MOBILE_NOT_EXISTS(1002013000, "手机号不存在"),

    SMS_SEND_MOBILE_TEMPLATE_PARAM_MISS(1002013001, "模板参数({})缺失"),

    SMS_SEND_TEMPLATE_NOT_EXISTS(1002013002, "短信模板不存在"),

    // ========== 短信验证码 1002014000 ==========
    SMS_CODE_NOT_FOUND(1002014000, "短信验证码不存在"),

    SMS_CODE_EXPIRED(1002014001, "短信验证码已过期"),

    SMS_CODE_USED(1002014002, "短信验证码已使用"),

    SMS_CODE_NOT_CORRECT(1002014003, "短信验证码不正确"),

    SMS_CODE_EXCEED_SEND_MAXIMUM_QUANTITY_PER_DAY(1002014004, "超过每日短信发送数量"),

    SMS_CODE_SEND_TOO_FAST(1002014005, "短信发送过于频率"),

    SMS_CODE_IS_EXISTS(1002014006, "手机号已被使用"),

    SMS_CODE_IS_UNUSED(1002014007, "验证码未被使用"),

    SMS_CODE_OVER_FAILED_TIME(1002014007, "验证码超过最大校验次数"),

    // ========== 租户信息 1002015000 ==========
    TENANT_NOT_EXISTS(1002015000, "租户不存在"),

    TENANT_DISABLE(1002015001, "名字为【{}】的租户已被禁用"),

    TENANT_EXPIRE(1002015002, "名字为【{}】的租户已过期"),

    TENANT_CAN_NOT_UPDATE_SYSTEM(1002015003, "系统租户不能进行修改、删除等操作！"),

    // ========== 租户套餐 1002016000 ==========
    TENANT_PACKAGE_NOT_EXISTS(1002016000, "租户套餐不存在"),

    TENANT_PACKAGE_USED(1002016001, "租户正在使用该套餐，请给租户重新设置套餐后再尝试删除"),

    TENANT_PACKAGE_DISABLE(1002016002, "名字为【{}】的租户套餐已被禁用"),

    // ========== 错误码模块 1002017000 ==========
    ERROR_CODE_NOT_EXISTS(1002017000, "错误码不存在"),

    ERROR_CODE_DUPLICATE(1002017001, "已经存在编码为【{}】的错误码"),

    // ========== 系统敏感词 1002019000 =========
    SENSITIVE_WORD_NOT_EXISTS(1002019000, "系统敏感词在所有标签中都不存在"),

    SENSITIVE_WORD_EXISTS(1002019001, "系统敏感词已在标签中存在"),

    // ========== 导入存量数据 1002020000 =========
    EXCEL_PARSING_FAILED(1002020001, "解析excel失败，文件不符合模板. [{}]"),

    IMPORT_LIMIT_REACHED(1002020002, "导入已达上限"),

    ;


    private int code;
    private String msg;
}
