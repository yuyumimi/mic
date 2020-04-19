package com.mic.demo.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @SentinelResource(value = "sayHello")
    public String sayHello(String name)throws BlockException {
        int a=1/0;
        return "Hello, " + name;
    }
}