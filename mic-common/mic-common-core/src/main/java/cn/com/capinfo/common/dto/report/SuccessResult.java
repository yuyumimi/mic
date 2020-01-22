package cn.com.capinfo.common.dto.report;

import lombok.Data;

/**
 * @ClassName SuccessResult
 * @Description 提交成功返回
 * @Author cmy
 * @Date 2019-12-11 15:31
 * @Version 1.0
 **/
@Data
public class SuccessResult {
    /*
    * 流水号
    * */
    private String reportId;
    /*
    * 创建时间
    * */
    private String createTime;
}
