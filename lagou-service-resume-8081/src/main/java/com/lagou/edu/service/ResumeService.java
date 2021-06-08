package com.lagou.edu.service;


import com.lagou.edu.pojo.Resume;
import org.springframework.stereotype.Service;

/**
 * @author Zhang
 * @date 6/2/2021 9:38 AM
 * @description
 */

public interface ResumeService {
    Resume findDefaultResumeByUserId(Long userId);
}
