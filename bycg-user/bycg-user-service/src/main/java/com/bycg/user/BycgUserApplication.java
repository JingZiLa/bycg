package com.bycg.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 用户服务启动类
 * @className: BycgUserApplication
 * @Author: Mirror
 * @CreateDate: 2020/5/13 17:08
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.bycg.user.mapper")
public class BycgUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BycgUserApplication.class,args);
    }
}
