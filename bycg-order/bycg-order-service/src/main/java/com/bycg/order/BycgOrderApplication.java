package com.bycg.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 订单服务启动类
 * @className: BycgOrderApplication
 * @Author: Mirror
 * @CreateDate: 2020/5/14 11:43
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.bycg.order.mapper")
public class BycgOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BycgOrderApplication.class,args);
    }
}
