package com.mic.user.controller;

import cn.com.capinfo.common.dto.CommonResult;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(value = "测试",tags = "测试用例")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @ApiOperation(value = "puttest",notes = "puttest测试说明2")
    @PutMapping(value = "/puttest")
    @ApiImplicitParam(value = "code",name = "code",dataType = "string",paramType = "query",defaultValue = "abc")
    public CommonResult puttest(String code){
        return CommonResult.builder().code(code).build();
    }





}
