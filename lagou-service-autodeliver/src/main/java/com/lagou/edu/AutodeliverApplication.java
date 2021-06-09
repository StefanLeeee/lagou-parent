package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Zhang
 * @date 6/3/2021 1:45 PM
 * @description
 */

/**
 * 注解简化写法
 * @SpringCloudApplication = @SpringBootApplication + @EnableDiscoveryCLient + @EnableCircuitBreaker
 */

@SpringBootApplication
@EnableDiscoveryClient   //开启服务发现
@EnableCircuitBreaker
public class AutodeliverApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication.class, args);
    }

    /**
     * 注入RestTemplate
     */

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
