package com.bycg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 文件微服务启动类
 * @className: BycgUploadApplication
 * @Author: Mirror
 * @CreateDate: 2020/5/12 19:22
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class BycgUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(BycgUploadApplication.class,args);
    }
}
