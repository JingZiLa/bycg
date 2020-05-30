package com.bycg.manage.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 后台管理启动类
 * @className: BycgManageWebApplication
 * @Author: Mirror
 * @CreateDate: 2020/5/10 15:19
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BycgManageWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(BycgManageWebApplication.class,args);
    }
}
