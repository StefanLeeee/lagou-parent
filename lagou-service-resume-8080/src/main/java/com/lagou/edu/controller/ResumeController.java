package com.lagou.edu.controller;


import com.lagou.edu.service.ResumeService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhang
 * @date 6/2/2021 10:00 AM
 * @description
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Value("${server.port}")
    private Integer port;



    /**
     * 获取简历开放状态的url：/resume/openstate/{userId}
     * @param userId ⽤户id
     * @return 0-关闭，1-打开，2-简历未达到投放标准被动关闭 3-从未设置过开放简历
     */
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable Long userId) {

        //模拟请求超时，触发服务消费者熔断降级
        try{
            Thread.sleep(3000);

        }catch(InterruptedException e) {
            e.printStackTrace();

        }
        System.out.println("===============>  8080  ");
        return port;
    }
}
