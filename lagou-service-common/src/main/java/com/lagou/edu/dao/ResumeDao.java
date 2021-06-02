package com.lagou.edu.dao;

import com.lagou.edu.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zhang
 * @date 6/1/2021 8:32 PM
 * @description
 */
public interface ResumeDao extends JpaRepository<Resume,Long> {
}
