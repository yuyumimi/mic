package com.mic.demo.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CustomLB {

    private AtomicInteger nextServerCyclicCounter = new AtomicInteger(2147483645);


    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int modulo = serviceInstances.size();
        return serviceInstances.get(incrementAndGetModulo(modulo));
    }


    private int incrementAndGetModulo(int modulo) {
        int next;
        int current;
        do {
            current = nextServerCyclicCounter.get();
            next = current >= 2147483647 ? 0 : (current + 1);
        } while (!nextServerCyclicCounter.compareAndSet(current, next));
        System.out.println("次数："+next);
        return next % modulo;
    }
}
