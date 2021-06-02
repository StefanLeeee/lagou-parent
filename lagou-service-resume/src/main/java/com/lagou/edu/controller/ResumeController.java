package com.lagou.edu.controller;



import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/resume/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable Long userId) {

        return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();

    }
}
