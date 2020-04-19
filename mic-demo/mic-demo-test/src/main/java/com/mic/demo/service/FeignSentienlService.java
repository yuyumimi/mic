package com.mic.demo.service;

import com.mic.common.dto.CommonResult;
import com.mic.demo.sentinel.UserBlockHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "mic-user",url = "http://localhost:8081/api",fallback = UserBlockHandler.class)
public interface FeignSentienlService {
    @GetMapping(value = "/user")
    public CommonResult<String> user();

}
