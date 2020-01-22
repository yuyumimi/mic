package cn.com.capinfo.common.exception;

import lombok.Getter;

/**
 * @author yuyu
 */

@Getter
public enum SystemErrorType implements ErrorType {

    /**
     * 错误类型码
     */
    SYSTEM_ERROR("-1", "系统异常,未知错误"),
    SYSTEM_BUSY("000001", "系统繁忙,请稍候再试"),
    ARGUMENT_NOT_VALID("000002", "请求参数校验错误"),
    UPLOAD_FILE_SIZE_LIMIT("000003", "上传文件大小超过限制"),
    DUPLICATE_PRIMARY_KEY("300000", "唯一键冲突"),
    TOO_MANY_RESULTS("300001", "查询结果与预期不符"),
    INTERNAL_SERVER_ERROR("000500", "服务器内部错误"),
    VALIDATE_FAILED("000400", "参数校验失败"),
    NOT_FOUND("000400", "页面找不到"),
    UNAUTHORIZED("000401", "未认证"),
    FORBIDDEN("000403", "未授权"),
    UNSUPPORTED_MEDIA_TYPE("000415", "不支持媒体类型"),
    DATABASE_FAILED("000500", "数据库错误"),

    SENTINEL_BLOCK("000900", "Sentinel block"),
    SENTINEL_FALLBACK("000901", "Sentinel fallback");
    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    SystemErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
