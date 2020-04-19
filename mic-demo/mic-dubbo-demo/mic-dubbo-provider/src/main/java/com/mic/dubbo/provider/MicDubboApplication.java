package com.mic.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicDubboApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicDubboApplication.class, args);
    }
}
