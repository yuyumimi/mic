package com.mic.common.exception;

import lombok.Getter;

/**
 * @ClassName BizErrorType
 * @Description 业务错误码
 * @Author yuyu
 * @Date 2019/11/13 16:19
 * @Version 1.0
 **/
@Getter
public enum BizErrorType implements ErrorType{
    /**
     * 申报
     */
    SEND_FAILED("200100", "提交接口失败"),
    RECEIVE_FAILED("200110", "反馈接口失败"),
    UNAUTHORIZED_NOT_INSURANCE("200200", "未参加养老保险"),
    ARGUMENT_NOT_VALID("200201", "请求参数校验不通过"),
    DATABASE_FAILED("200500", "数据库错误");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    BizErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
