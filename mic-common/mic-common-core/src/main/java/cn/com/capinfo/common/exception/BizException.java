package cn.com.capinfo.common.exception;

/**
 *@ClassName BusinessException
 *@Description 全局业务异常
 *@Author yuyu
 *@Date 2019/11/13 10:39
 *@Version 1.0
 **/
public class BizException extends BaseException {

    public BizException() {

    }

    public BizException(ErrorType status) {
        super(status);
    }
}
