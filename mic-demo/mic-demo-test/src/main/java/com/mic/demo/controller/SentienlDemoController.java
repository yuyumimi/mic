package com.mic.demo.controller;

import com.mic.common.dto.CommonResult;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mic.demo.sentinel.BlockHandler;
import com.mic.demo.service.FeignSentienlService;
import com.mic.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 使用方法
 * https://github.com/alibaba/Sentinel/wiki/%E4%B8%BB%E9%A1%B5
 */
@RestController
@Slf4j
public class SentienlDemoController {

    @GetMapping(value = "/handler")
    @SentinelResource(value = "CustomerBlockHandler"
            , blockHandlerClass = BlockHandler.class
            , blockHandler = "handlerBlock"
            , fallbackClass = BlockHandler.class
            , fallback = "handlerException"
    )
    public String testHotKey(@RequestParam int id) throws IllegalAccessException {
        if (id == 1) {
            int a = 1 / 0;
        }
        return "hotkey";
    }

    @Autowired
    private TestService service;

    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable String name) {
        String s = null;
        try {
            s = service.sayHello(name);
        } catch (BlockException be) {
            log.info("BlockException", be);
        }
        return s;
    }

    @Resource
    private FeignSentienlService feignSentienlService;

    @GetMapping(value = "fegin")
    public CommonResult<String> test(){
        return this.feignSentienlService.user();
    }
}
