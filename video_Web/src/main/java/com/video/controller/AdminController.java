package com.video.controller;

import com.video.pojo.Admin;
import com.video.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 15:26
 */
@RequestMapping("admin")
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping("toLoginView")
    public String toLoginView() {

        return "/behind/login.jsp";
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(Admin admin, HttpServletRequest request) {

        System.out.println("执行登录");

        List<Admin> userByNameAndPsw = adminService.findUserByNameAndPsw(admin.getUsername(), admin.getPassword());

        if (!(userByNameAndPsw.isEmpty())) {
            System.out.println(userByNameAndPsw);
            HttpSession session = request.getSession(true);
            session.setAttribute("username", admin.getUsername());
            session.setMaxInactiveInterval(60 * 60);
            return  "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping("exit")
    public String exit(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
//        销毁Session
        session.invalidate();

        return "/behind/login.jsp";
    }
}