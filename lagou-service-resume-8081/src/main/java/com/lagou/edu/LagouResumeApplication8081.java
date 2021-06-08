package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Zhang
 * @date 6/2/2021 10:03 AM
 * @description
 */

@SpringBootApplication
@EntityScan("com.lagou.edu.pojo")
//@EnableEurekaClient  //开启Eureka Client （Eureka独有）
@EnableDiscoveryClient  ///开启注册中心客户端（通用型注解，比如注册到Eureka、Nacos等）
                        //说明：从SpringCloud的Edgware版本开始，不加注解也ok，但是建议加上
public class LagouResumeApplication8081 {
    public static void main(String[] args) {
        SpringApplication.run(LagouResumeApplication8080.class, args);
    }
}
