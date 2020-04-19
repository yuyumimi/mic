package com.mic.demo.sentinel;

import com.mic.common.dto.CommonResult;
import com.mic.demo.service.FeignSentienlService;
import org.springframework.stereotype.Component;

@Component
public class UserBlockHandler implements FeignSentienlService {
    @Override
    public CommonResult<String> user() {
        return CommonResult.<String>builder().data("UserBlockHandler").build();
    }
}
