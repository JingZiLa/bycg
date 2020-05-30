package com.bycg.portal.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description:
 * @className:
 * @Author: Mirror
 * @CreateDate: 2020/5/15 11:03
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BycgPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BycgPortalApplication.class,args);
    }
}
