package com.bycg.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @className:
 * @Author: Mirror
 * @CreateDate: 2020/5/21 16:15
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BycgAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BycgAuthApplication.class,args);
    }
}
