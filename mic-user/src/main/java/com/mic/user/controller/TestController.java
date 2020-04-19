package com.mic.user.controller;

import com.mic.common.dto.CommonResult;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mic.user.dto.MicUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Api(value = "测试",tags = "测试用例")
@RestController
@RequestMapping("/api")
@Slf4j
public class TestController {

    @Autowired
    private ServerProperties properties;

    @ApiOperation(value = "test",notes = "puttest测试说明2")
    @GetMapping(value = "/test")
    @ApiImplicitParam(value = "code",name = "code",dataType = "string",paramType = "query",defaultValue = "abc")
    public CommonResult<MicUser> puttest(@NotNull String code){
        MicUser user=new MicUser();
        user.setIdcard("110105");
        user.setName("yuyu");
        user.setPort(properties.getPort());
        user.setDate(LocalDateTime.now());
        user.setNow(new Date());

        return CommonResult.<MicUser>builder().data(user).build();
    }

    Cache<String, List> cache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS).build();
    @GetMapping(value = "/user")
    public CommonResult<String> user(){
        ArrayList a = new ArrayList();
        a.add("23");
        cache.put("word",a);
        log.info(""+LocalDateTime.now());
        return CommonResult.<String>builder().data("user").build();
    }
    @GetMapping(value = "/user1")
    public CommonResult<String> user1(){
        System.out.println(cache.getIfPresent("word"));
        cache.getIfPresent("word").clear();
        log.info(""+LocalDateTime.now());
        return CommonResult.<String>builder().data("user").build();
    }


}
