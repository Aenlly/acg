package top.aenlly.acg.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Infra 错误码枚举类
 * <p>
 * infra 系统，使用 1-001-000-000 段
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum FileErrorCode implements ErrorCode {

    // ========== 参数配置 1001000000 ==========
    CONFIG_NOT_EXISTS(1001000001, "参数配置不存在"),
    CONFIG_KEY_DUPLICATE(1001000002, "参数配置 key 重复"),
    CONFIG_CAN_NOT_DELETE_SYSTEM_TYPE(1001000003, "不能删除类型为系统内置的参数配置"),
    CONFIG_GET_VALUE_ERROR_IF_VISIBLE(1001000004, "获取参数配置失败，原因：不允许获取不可见配置"),

    // ========== 定时任务 1001001000 ==========
    JOB_NOT_EXISTS(1001001000, "定时任务不存在"),
    JOB_HANDLER_EXISTS(1001001001, "定时任务的处理器已经存在"),
    JOB_CHANGE_STATUS_INVALID(1001001002, "只允许修改为开启或者关闭状态"),
    JOB_CHANGE_STATUS_EQUALS(1001001003, "定时任务已经处于该状态，无需修改"),
    JOB_UPDATE_ONLY_NORMAL_STATUS(1001001004, "只有开启状态的任务，才可以修改"),
    JOB_CRON_EXPRESSION_VALID(1001001005, "CRON 表达式不正确"),

    // ========== API 错误日志 1001002000 ==========
    API_ERROR_LOG_NOT_FOUND(1001002000, "API 错误日志不存在"),
    API_ERROR_LOG_PROCESSED(1001002001, "API 错误日志已处理"),

    // ========= 文件相关 1001003000=================
    FILE_PATH_EXISTS(1001003000, "文件路径已存在"),
    FILE_NOT_EXISTS(1001003001, "文件不存在"),
    FILE_IS_EMPTY(1001003002, "文件为空"),
    FILE_TYPE_EMPTY(1001003002, "文件业务类型"),
    FOLDER_IS_EMPTY(1001003002, "文件夹为空"),
    FILE_NOT_SUPPORT(1001003003, "文件格式不支持"),
    FILE_ACCESS_DENIED(1001003001, "文件权限不足"),
    FILE_IMAGE_SIZE_LIMIT(1001003004, "文件大小限制"),
    FILE_CONTENT_ERROR(1001003004, "获取文件内容异常"),

    // ========== 代码生成器 1001004000 ==========
    CODEGEN_TABLE_EXISTS(1003001000, "表定义已经存在"),
    CODEGEN_IMPORT_TABLE_NULL(1003001001, "导入的表不存在"),
    CODEGEN_IMPORT_COLUMNS_NULL(1003001002, "导入的字段不存在"),
    CODEGEN_TABLE_NOT_EXISTS(1003001004, "表定义不存在"),
    CODEGEN_COLUMN_NOT_EXISTS(1003001005, "字段义不存在"),
    CODEGEN_SYNC_COLUMNS_NULL(1003001006, "同步的字段不存在"),
    CODEGEN_SYNC_NONE_CHANGE(1003001007, "同步失败，不存在改变"),
    CODEGEN_TABLE_INFO_TABLE_COMMENT_IS_NULL(1003001008, "数据库的表注释未填写"),
    CODEGEN_TABLE_INFO_COLUMN_COMMENT_IS_NULL(1003001009, "数据库的表字段({})注释未填写"),

    // ========== 字典类型（测试）1001005000 ==========
    TEST_DEMO_NOT_EXISTS(1001005000, "测试示例不存在"),

    // ========== 文件配置 1001006000 ==========
    FILE_CONFIG_NOT_EXISTS(1001006000, "文件配置不存在"),
    FILE_CONFIG_DELETE_FAIL_MASTER(1001006001, "该文件配置不允许删除，原因：它是主配置，删除会导致无法上传文件"),

    // ========== 数据源配置 1001007000 ==========
    DATA_SOURCE_CONFIG_NOT_EXISTS(1001007000, "数据源配置不存在"),
    DATA_SOURCE_CONFIG_NOT_OK(1001007001, "数据源配置不正确，无法进行连接"),
    ;

    private int code;
    private String msg;
}
