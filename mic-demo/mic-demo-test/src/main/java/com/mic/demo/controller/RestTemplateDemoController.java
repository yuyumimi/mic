package com.mic.demo.controller;

import com.mic.demo.config.CustomLB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class RestTemplateDemoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient client;

    @GetMapping(value = "test")
    public String test() {
        String url = "http://mic-user/api/test";
        String body = this.restTemplate.getForEntity(url, String.class).getBody();
        return body;
    }

    @Autowired
    private CustomLB lb;

    @GetMapping(value = "lb")
    public String lb() {
        List<ServiceInstance> instances = client.getInstances("mic-user");
        ServiceInstance instance = lb.instance(instances);
        log.info(instance.getUri() + "");
        return this.restTemplate.getForEntity(instance.getUri() + "/api/test", String.class).getBody();
    }

}
