package com.bycg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: eureka注册中心启动类
 * @className: RegistryApplication
 * @Author: Mirror
 * @CreateDate: 2020/5/9 10:08
 **/
@SpringBootApplication
@EnableEurekaServer
public class BycgRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(BycgRegistryApplication.class,args);
    }
}
