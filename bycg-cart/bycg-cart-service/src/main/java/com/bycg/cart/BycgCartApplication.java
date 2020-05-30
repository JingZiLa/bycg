package com.bycg.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 购物车服务启动类
 * @className: BycgCartApplication
 * @Author: Mirror
 * @CreateDate: 2020/5/22 11:53
 **/
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.bycg.cart.mapper")
public class BycgCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(BycgCartApplication.class,args);

    }
}
