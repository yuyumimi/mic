package cn.com.capinfo.gateway.config;

import cn.com.capinfo.gateway.properties.RunDateDateProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AutoConfigurationConfig
 * @Description TODO
 * @Author yuyu
 * @Date 2019/12/19 12:24
 * @Version 1.0
 **/
@Configuration
@EnableConfigurationProperties(RunDateDateProperties.class)
public class AutoConfigurationConfig {
}
