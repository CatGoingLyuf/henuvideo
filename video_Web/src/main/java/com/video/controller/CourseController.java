package com.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.pojo.Course;
import com.video.pojo.QueryVo;
import com.video.pojo.Subject;
import com.video.service.CourseService;
import com.video.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping("showCourseList")
    public ModelAndView list (@RequestParam(required = false,defaultValue = "1") Integer pageNum){
        ModelAndView modelAndView = new ModelAndView();
        PageHelper.startPage(pageNum,10);
        List<Subject> subjectList = subjectService.findAll();
        modelAndView.addObject("subjectList",subjectList);
        List<Course> courseList = courseService.findAll();
        PageInfo<Course> coursePageInfo = new PageInfo<>(courseList);
        modelAndView.addObject("pageInfo",coursePageInfo);
        System.out.println(coursePageInfo);
        modelAndView.setViewName("/behind/courseList.jsp");
        return modelAndView;
    }

    @RequestMapping("addCourse")
    public ModelAndView addCourse(){


        Course course = new Course();
        ModelAndView modelAndView = new ModelAndView();
        List<Subject> subjectList = subjectService.findAll();
        modelAndView.addObject("subjectList",subjectList);
        modelAndView.addObject("course",course);
        modelAndView.setViewName("/behind/addCourse.jsp");
        return modelAndView;
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Course course){
        if (course.getId() == null){
            System.out.println(course);
            courseService.addCourse(course);
        }else {
            courseService.updateCourse(course);
        }
        return "redirect:/course/showCourseList";
    }

    @RequestMapping("edit")
    public ModelAndView edit(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Subject> subjectList = subjectService.findAll();
        modelAndView.addObject("subjectList",subjectList);
        Course byId = courseService.findById2(id);
        modelAndView.addObject("course", byId);

        modelAndView.setViewName("/behind/addCourse.jsp");

        return modelAndView;
    }

    @RequestMapping("delCourse")
    @ResponseBody
    public String delCourse(Course course) {

        courseService.delCourse(course);

        System.out.println("删除的id为:" + course.getId());

        return "success";
    }
}
