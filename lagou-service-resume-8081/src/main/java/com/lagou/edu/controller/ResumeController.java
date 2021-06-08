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


    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable Long userId) {

        System.out.println(userId);
//        System.out.println(resumeService.findDefaultResumeByUserId(userId).getIsOpenResume());
//        return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();
        System.out.println("===============>  8080  ");
        return port;
    }
}
