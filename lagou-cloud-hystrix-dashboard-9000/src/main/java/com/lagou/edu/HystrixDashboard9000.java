package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author Zhang
 * @date 6/9/2021 7:55 PM
 * @description
 */

@SpringBootApplication
@EnableHystrixDashboard  // 开启hystrix dashboard
public class HystrixDashboard9000 {
    public static void main(String[] args){
        SpringApplication.run(HystrixDashboard9000.class, args);

    }
}
