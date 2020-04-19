package com.mic.common.dto.report;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Attachments
 * @Description 审核附件（身份证户口本）
 * @Author yuyu
 * @Date 2019/11/4 15:41
 * @Version 1.0
 **/
@Data
public class ReportFile implements Serializable {

    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件二进制数据
     */
    private byte[] file;
    /**
     * 类型
     * 1	本人身份证
     * 2	户口本
     * 3	承诺书
     * 4	受益人或法定继承人身份证
     * 5	告知书
     */
    private Short type;
}
