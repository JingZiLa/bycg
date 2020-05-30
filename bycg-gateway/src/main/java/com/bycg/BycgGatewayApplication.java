package com.bycg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Description: 网关启动类
 * @className: BycgGatewayApplication
 * @Author: Mirror
 * @CreateDate: 2020/5/9 10:14
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class BycgGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(BycgGatewayApplication.class,args);
    }
}
