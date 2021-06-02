package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author Zhang
 * @date 6/2/2021 10:03 AM
 * @description
 */

@SpringBootApplication
@EntityScan("com.lagou.edu.pojo")
public class LagouResumeApplication {
    public static void main(String[] args) {
        SpringApplication.run(LagouResumeApplication.class, args);
    }
}
