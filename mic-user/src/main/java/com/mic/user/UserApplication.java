package com.mic.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName TestApplication
 * @Description TODO
 * @Author yuyu
 * @Date 2020/1/18 14:57
 * @Version 1.0
 **/
@EnableTransactionManagement
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
