package com.mic.dubbo.provider.service;


import com.mic.dubbo.api.MicDemoApi;
import com.mic.dubbo.api.bo.MicUser;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0", interfaceName = "MicDemoService", timeout
        = 10000, loadbalance = "roundrobin", retries = 0)
public class MicDemoService implements MicDemoApi {

    @Override
    public MicUser info(String name) {
        if ("yuyu".equals(name)) {
            MicUser micUser = new MicUser();
            micUser.setId("1");
            micUser.setName(name);
            micUser.setPhone("112");
            return micUser;
        }
        MicUser micUser = new MicUser();
        micUser.setId("1");
        micUser.setName("demo");
        micUser.setPhone("132");
        return micUser;
    }
}
