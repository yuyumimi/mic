package cn.com.capinfo.common.exception;


import lombok.Getter;

@Getter
public enum GatewayErrorType implements ErrorType{
    /**
     *
     */
    GATEWAY_NOT_FOUND_SERVICE("100404", "网关未找到服务"),
    GATEWAY_ERROR("100500", "网关异常"),
    GATEWAY_UNAUTHORIZED("100401", "未授权"),
    GATEWAY_FORBIDDEN("100403", "没有权限"),
    GATEWAY_MAINTAIN("100503", "系统维护中"),
    GATEWAY_CONNECT_TIME_OUT("100504", "网关超时");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    GatewayErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
