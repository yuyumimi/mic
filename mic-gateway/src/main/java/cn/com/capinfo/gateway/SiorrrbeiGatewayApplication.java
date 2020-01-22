package cn.com.capinfo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SiorrrbeiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiorrrbeiGatewayApplication.class, args);
    }

}
