package com.video.controller;

import com.video.pojo.Subject;
import com.video.service.CourseService;
import com.video.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author: lyuf
 * @date: 2020/10/19 19:59
 */
@Controller
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/course/{subId}")
    public ModelAndView course(@PathVariable(name = "subId") Integer subId) {
        ModelAndView modelAndView = new ModelAndView();

        Subject subject = subjectService.findBySubjectId(subId);

        modelAndView.addObject("subject", subject);

        modelAndView.setViewName("/before/course.jsp");

        return modelAndView;
    }
}
