package com.bycg.item;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 商品服务启动类
 * @className: BycgItemApplication
 * @Author: Mirror
 * @CreateDate: 2020/5/9 15:39
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.bycg.item.mapper")
public class BycgItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BycgItemApplication.class,args);
    }
}
