package cn.com.capinfo.common.dto;

import org.springframework.validation.FieldError;

import java.io.Serializable;

/**
 * @ClassName ErrorField
 * @Description TODO
 * @Author yuyu
 * @Date 2019/7/25 13:40
 * @Version 1.0
 **/
public class ErrorField implements Serializable {

    private String field;
    private Object rejectedValue;
    private String defaultMessage;

    public ErrorField(FieldError fieldError) {
        this.field = fieldError.getField();
        this.rejectedValue = fieldError.getRejectedValue();
        this.defaultMessage = fieldError.getDefaultMessage();
    }

    public ErrorField(String field,String rejectedValue,String defaultMessage){
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.defaultMessage = defaultMessage;
    }

    public String getField() {
        return this.field;
    }

    public Object getRejectedValue() {
        return this.rejectedValue;
    }

    public String getDefaultMessage() {
        return this.defaultMessage;
    }
}
