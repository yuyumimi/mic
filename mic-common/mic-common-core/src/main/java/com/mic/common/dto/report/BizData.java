package com.mic.common.dto.report;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BizData
 * @Description TODO
 * @Author yuyu
 * @Date 2019/11/5 10:48
 * @Version 1.0
 **/

@Data
public class BizData implements Serializable {
    /**
     * 发送约定好的json数据
     */
    private Object json;
    /**
     *身份证
     */
    private String idcard;
    /**
     *姓名
     */
    private String name;
    /**
     *业务类型
     */
    private Short biztype;
    /**
     *申报交易流水号
     */
    private String reportid;
    /**
     *业务交易流水号
     */
    private String dataId;
    /**
     * 申报文件
     */
    private ReportFile[] files;
}
