package com.mic.common.dto.report;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ReportUrl
 * @Description TODO
 * @Author yuyu
 * @Date 2019/11/4 14:08
 * @Version 1.0
 **/
@Data
public class ReportUrl implements Serializable {
    /**
     * 发送地址
     */
    private String url;

    private BizData data;
}
