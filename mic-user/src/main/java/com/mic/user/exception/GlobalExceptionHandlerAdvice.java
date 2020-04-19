package com.mic.user.exception;

import com.mic.common.exception.BizException;
import com.mic.common.exception.DefaultGlobalExceptionHandlerAdvice;
import com.mic.common.dto.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {BizException.class})
    public CommonResult bizException(BizException ex) {
        log.error("业务类异常",ex);
        return CommonResult.builder().message(ex.getMessage()).build();
    }
}
