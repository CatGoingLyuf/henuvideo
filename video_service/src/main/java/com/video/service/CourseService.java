package com.video.service;

import com.video.pojo.Course;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 21:48
 */
public interface CourseService {
    List<Course> findAll();
    public List<Course> course(@PathVariable(name = "subject.id") Integer subjectId);
    Course findById(Integer courseId);
}
