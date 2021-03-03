package com.video.controller;

import com.video.pojo.Subject;
import com.video.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: lyuf
 * @date: 2020/10/19 16:00
 */
@Controller
@RequestMapping("subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @RequestMapping("selectAll")
    public String selectAll(HttpServletRequest request) {
        List<Subject> subjectList = subjectService.findAll();

        request.getSession().setAttribute("subjectList", subjectList);

        return "/before/index.jsp";
    }
}
