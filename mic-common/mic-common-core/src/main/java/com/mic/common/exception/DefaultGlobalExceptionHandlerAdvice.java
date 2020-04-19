package com.mic.common.exception;

import com.mic.common.dto.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * @ClassName DefaultGlobalExceptionHandlerAdvice
 * @Description 默认全局异常类，RestControllerAdvice需继承此类
 * @Author yuyu
 * @Date 2019/11/28 14:48
 * @Version 1.0
 **/
@RestControllerAdvice
@Slf4j
public class DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public CommonResult missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error("missing servlet request parameter exception:{}",
                ex.getMessage(), ex);
        return CommonResult.builder().code(
                SystemErrorType.ARGUMENT_NOT_VALID.getCode()).build();
    }

    @ExceptionHandler(value = {MultipartException.class})
    public CommonResult uploadFileLimitException(MultipartException ex) {
        log.error("upload file size limit:{}", ex.getMessage(), ex);
        return CommonResult.builder().build().failed(
                SystemErrorType.UPLOAD_FILE_SIZE_LIMIT);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public CommonResult methodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        log.error("methodArgumentNotValid exception:{}", ex.getMessage(), ex);
        return CommonResult.builder().build().allFailed(
                BizErrorType.ARGUMENT_NOT_VALID,
                ex.getBindingResult());
    }

    @ExceptionHandler(value = {BindException.class})
    public CommonResult bindException(BindException ex) {
        log.error("bind exception:{}", ex.getMessage(), ex);
        return CommonResult.builder().build().allFailed(
                BizErrorType.ARGUMENT_NOT_VALID,
                ex.getBindingResult());
    }

    @ExceptionHandler(value = {BaseException.class})
    public CommonResult baseException(BaseException ex) {
        log.error("base exception:{}", ex.getMessage(), ex);
        return CommonResult.builder().build().failed(ex.getErrorType());
    }

//    @ExceptionHandler(value = {AccessDeniedException.class})
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public CommonResult accessDeniedException(AccessDeniedException ex) {
//        log.error("accessDenied exception:{}", ex.getMessage(), ex);
//        return CommonResult.builder().build().failed(
//                SystemErrorType.FORBIDDEN);
//    }

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public CommonResult exception(HttpMediaTypeNotSupportedException ex) {
        log.error("HttpMediaTypeNotSupportedException:{}", ex.getMessage(), ex);
        return CommonResult.builder().build().failed(
                SystemErrorType.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public CommonResult exception(HttpRequestMethodNotSupportedException ex) {
        log.error("RequestMethodNotSupportedException:{}", ex.getMessage(), ex);
        return CommonResult.builder().build().failed();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult exception(Exception ex) {
        log.error("exception:{}", ex.getMessage(), ex);
        return CommonResult.builder().build().failed();
    }

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult notFoundException(NoHandlerFoundException ex) throws Exception {
        //todo 获得用户，可以统计哪个用户恶意访问
        log.warn("noHandlerFoundException:{}", ex.getMessage());
        return CommonResult.builder().build().failed(
                SystemErrorType.NOT_FOUND);
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult throwable(Throwable t) {
        log.error("Throwable {}", t.getMessage(), t);
        return CommonResult.builder().build().failed();
    }
}
