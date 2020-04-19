package com.mic.dubbo.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicDubboConsumorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicDubboConsumorApplication.class, args);
    }
}
