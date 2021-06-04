package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Zhang
 * @date 6/3/2021 4:54 PM
 * @description
 */

@SpringBootApplication
@EnableEurekaServer
public class LagouEurakaServerApp8761 {

    public static void main(String[] args) {
        SpringApplication.run(LagouEurakaServerApp8761.class, args);
    }
}
