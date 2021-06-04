package com.lagou.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Zhang
 * @date 6/2/2021 10:35 AM
 * @description
 */

@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

//    @GetMapping("/checkState/{userId}")
//    public Integer findResumeOpenState(@PathVariable Long userId) {
//        Integer forObject = restTemplate.getForObject("http://localhost:8080/resume/openstate/" + userId, Integer.class);
//        System.out.println("=================>调用简历微服务，获取到用户"+userId+"的默认简历，当前状态为："+forObject);
//        return forObject;
//    }


    @GetMapping("/checkAndBegin/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        //1.获取Eureka中注册的user-service 实例列表
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("lagou-service-resume");
        //2.获取实例（此处我们不考虑负载，就拿第一个）
        ServiceInstance serviceInstance = serviceInstanceList.get(0);
        //3.获取实例的信息拼接的请求地址
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = "http://" + host + ":" + port + "/resume/openstate/" + userId;


        Integer forObject = restTemplate.getForObject(url + userId, Integer.class);
        System.out.println("=================>调用简历微服务，获取到用户"+userId+"的默认简历，当前状态为："+forObject);
        return forObject;
    }
}
