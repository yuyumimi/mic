package com.mic.common.exception;

/**
 *@ClassName ErrorType
 *@Description
 * 错误类型 规则000 000前三位 后三位
 * 前三位
 * 系统错误 000
 * 网关错误 100
 * 业务错误 200
 * 数据库错误 300
 * 后三位
 * 申报服务 100
 * 新参保   200
 * 转移接续 210
 * 权益220
 * 待遇申领 300
 * 如000100 申报服务系统错误
 *@Author yuyu
 *@Date 2019/11/13 15:08
 *@Version 1.0
 **/
public interface ErrorType {
    /**
     * 返回code
     *
     * @return
     */
    String getCode();

    /**
     * 返回msg
     *
     * @return
     */
    String getMsg();
}
