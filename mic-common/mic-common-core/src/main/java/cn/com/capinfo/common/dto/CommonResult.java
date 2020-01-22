package cn.com.capinfo.common.dto;

import cn.com.capinfo.common.exception.ErrorType;
import cn.com.capinfo.common.exception.SystemErrorType;
import cn.com.capinfo.common.util.FastJsonUtils;
import lombok.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用返回对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResult implements Serializable {
    //操作成功
    public static final String SUCCESS = "200";

    private String code;
    private String message;
    private Object data;
    private List<ErrorField> errors;
    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public CommonResult success(Object data) {
        this.code = SUCCESS;
        this.data = data;
        return this;
    }

    /**
     * 系统繁忙失败提示信息
     */
    public CommonResult failed() {
        this.code = SystemErrorType.SYSTEM_BUSY.getCode();
        this.message = SystemErrorType.SYSTEM_BUSY.getMsg();
        return this;
    }

    public CommonResult failed(ErrorType errorType) {
        this.code = errorType.getCode();
        this.message = errorType.getMsg();
        return this;
    }

    public CommonResult failedByArg(ErrorType errorType, Object data) {
        this.code = errorType.getCode();
        this.message = errorType.getMsg();
        this.data = data;
        return this;
    }

    public CommonResult failed(ErrorType errorType, Object data) {
        this.code = errorType.getCode();
        this.message = errorType.getMsg();
        this.data = data;
        return this;
    }

    /**
     * 参数验证失败使用
     *
     * @param result 错误信息
     */
    public CommonResult allFailed(ErrorType errorType, BindingResult result) {
        List<ObjectError> allErrors = result.getAllErrors();
        errors = new ArrayList<>(allErrors.size());
        allErrors.forEach(item -> {
            if (item instanceof FieldError) {
                ErrorField errorField = new ErrorField((FieldError) item);
                errors.add(errorField);
            }
        });
        this.code = errorType.getCode();
        this.message = errorType.getMsg();
        return this;
    }

    @Override
    public String toString() {
        return FastJsonUtils.convertObjectToJSON(this);
    }

}
