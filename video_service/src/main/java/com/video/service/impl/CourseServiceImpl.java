package com.video.service.impl;

import com.video.dao.CourseMapper;
import com.video.pojo.Course;
import com.video.pojo.CourseExample;
import com.video.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 21:48
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseMapper.selectByExample(null);
    }

    @Override
    public List<Course> course(@PathVariable(name="subject.id") Integer subjectId) {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andSubjectIdEqualTo(subjectId);
        List<Course> courseList = courseMapper.selectByExample(courseExample);

        return courseList;
    }

    @Override
    public Course findById2(Integer courseId) {
        return courseMapper.findById2(courseId);
    }
    @Override
    public Course findById(Integer courseId) {
        return courseMapper.findById(courseId);
    }
    @Override
    public void addCourse(Course course) {
        courseMapper.insert(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseMapper.updateByPrimaryKeyWithBLOBs(course);
    }

    @Override
    public void delCourse(Course course) {
        courseMapper.deleteByPrimaryKey(course.getId());
    }
}
