package com.mic.gateway.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @ClassName MaintainDateProperties
 * @Description TODO
 * @Author yuyu
 * @Date 2019/12/19 12:22
 * @Version 1.0
 **/
@Setter
@Getter
@ConditionalOnProperty(prefix = "siorrrbei.running-date", name = "enabled", havingValue = "true")
@ConfigurationProperties(prefix = "siorrrbei.running-date")
@RefreshScope
public class RunDateDateProperties {
    private boolean enable = true;
    private String ignoreUri="";
    private Integer startDay;
    private Integer endDay;
    private Integer startHour;
    private Integer endHour;
}
