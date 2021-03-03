package com.video.service;

import com.video.pojo.Subject;

import java.util.List;

/**
 * @author: lyuf
 * @date: 2020/10/19 16:07
 */
public interface SubjectService {
    public List<Subject> findAll();

    Subject findBySubjectId(Integer subjectId);

    Subject findBySubjectName(String subjectName);
}
