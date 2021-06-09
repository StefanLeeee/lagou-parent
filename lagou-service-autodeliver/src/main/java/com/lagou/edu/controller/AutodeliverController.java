package com.lagou.edu.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    /**
     * 服务注册到Eureka之后的改造
     * @param userId
     * @return
     */
    /*@GetMapping("/checkAndBegin/{userId}")
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
    }*/


    /**
     * 使用Ribbon负载均衡
     *
     * @param userId
     * @return
     */
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        //使用Ribbon不需要我们自己去获取服务实例然后选择一个那么去访问了（自己的负载均衡）
        String url = "http://lagou-service-resume/resume/openstate/" + userId;  //指定服务名
        Integer forObejct = restTemplate.getForObject(url, Integer.class);
        return forObejct;
    }

    /**
     * 提供者模拟处理超时，调用方法添加Hystrix控制
     */
//使用@HystrixCommand
    @HystrixCommand(
            // 线程池标识，要保持唯一，不唯一的话就共用了
            threadPoolKey = "findResumeOpenStateTimeout",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "1"), // 线程数
                    @HystrixProperty(name="maxQueueSize",value="20") // 等待队列长度
            },
            // commandProperties熔断的一些细节属性配置
            commandProperties = {
                    // 每一个属性都是一个HystrixProperty
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")
            }
    )
    @GetMapping("/checkStateTimeout/{userId}")
    public Integer findResumeOpenStateTimeout(@PathVariable Long userId) {

        //使用Ribbon不需要我们自己去获取服务实例然后选择一个那么去访问了（自己的负载均衡）
        String url = "http://lagou-service-resume/resume/openstate/" + userId;  //指定服务名
        Integer forObejct = restTemplate.getForObject(url, Integer.class);
        return forObejct;
    }



    @GetMapping("/findResumeOpenStateTimeoutFallback/{userId}")
    @HystrixCommand(
            // 线程池标识，要保持唯一，不唯一的话就共用了
            threadPoolKey = "findResumeOpenStateTimeoutFallback",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "2"), // 线程数
                    @HystrixProperty(name="maxQueueSize",value="20") // 等待队列长度
            },
            // commandProperties熔断的一些细节属性配置
            commandProperties = {
                    // 每一个属性都是一个HystrixProperty
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")
                    // hystrix高级配置，定制工作过程细节
                    ,
                    // 统计时间窗口定义
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "8000"),
                    // 统计时间窗口内的最小请求数
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "2"),
                    // 统计时间窗口内的错误数量百分比阈值
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                    // 自我修复时的活动窗口长度
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "3000")



            },
            fallbackMethod = "myFallBack"  // 回退方法
    )
    public Integer findResumeOpenStateTimeoutFallback(@PathVariable Long userId) {
        //使用Ribbon不需要我们自己去获取服务实例然后选择一个那么去访问了（自己的负载均衡）
        String url = "http://lagou-service-resume/resume/openstate/" + userId;  //指定服务名
        Integer forObejct = restTemplate.getForObject(url, Integer.class);
        return forObejct;
    }

    /**
     * 定义回退方法，返回预设默认值
     * 注意：该方法形参和返回值和原始方法保持一致
     * @param userId
     * @return
     */
    public Integer myFallBack(Long userId) {
        return -123333; //兜底数据
    }

    /**
     * 1）服务提供者处理超时，熔断，返回错误信息
     * 2）有可能服务提供者出现异常直接抛出异常信息
     *
     * 以上信息，都会返回到消费者这里，很多时候消费者服务不希望把收到的异常、错误信息再抛到它的上游去
     *     用户微服务-- 注册微服务-- 优惠券微服务
     *                 1、登记注册
     *                 2、分发优惠券（并不是核心业务），这里如果调用优惠券微服务返回了异常信息或者熔断后的错误信息，这些信息如果抛出给用户很不友好
     *                              此时，我们可以返回一个兜底数据，预设的默认值（服务降级）
     */
}
