package com.mic.dubbo.customer.controller;

import com.mic.common.dto.CommonResult;
import com.mic.dubbo.api.MicDemoApi;
import com.mic.dubbo.api.bo.MicUser;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Reference(version = "1.0.0",
            interfaceName = "MicDemoService",
            timeout = 5000,check = false
    )
    private MicDemoApi micDemoApi;

    @GetMapping(value = "/info")
    public CommonResult<MicUser> info(){
        MicUser user = this.micDemoApi.info("yuyu");
        CommonResult<MicUser> result = new CommonResult<>();
        return result.success(user);
    }

}
