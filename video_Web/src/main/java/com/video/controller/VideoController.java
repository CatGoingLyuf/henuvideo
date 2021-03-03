package com.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.pojo.Course;
import com.video.pojo.QueryVo;
import com.video.pojo.Speaker;
import com.video.pojo.Video;
import com.video.service.CourseService;
import com.video.service.SpeakerService;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 19:51
 */
@RequestMapping("video")
@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private SpeakerService speakerService;

    @Autowired
    private CourseService courseService;

    @RequestMapping("list")
    public ModelAndView list(QueryVo queryVo, @RequestParam(required = false,defaultValue = "1") Integer pageNum) {
        System.out.println(queryVo);
        System.out.println(pageNum);
        PageHelper.startPage(pageNum, 10);
        List<Video> allVideo = videoService.findAllVideo(queryVo);

        PageInfo<Video> pageInfo = new PageInfo<>(allVideo);
        System.out.println(pageInfo);
        ModelAndView modelAndView = new ModelAndView();

        List<Speaker> speakerList = speakerService.findAll();
        modelAndView.addObject("speakerList", speakerList);
        List<Course> courseList = courseService.findAll();
        modelAndView.addObject("courseList", courseList);

        modelAndView.addObject("queryVo",queryVo);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("/behind/videoList.jsp");

        return modelAndView;
    }

    @RequestMapping("edit")
    public ModelAndView edit(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Video byId = videoService.findById(id);
        System.out.println(byId);
        modelAndView.addObject("video", byId);
        List<Speaker> speakerList = speakerService.findAll();
        modelAndView.addObject("speakerList", speakerList);
        List<Course> courseList = courseService.findAll();
        modelAndView.addObject("courseList", courseList);

        modelAndView.setViewName("/behind/addVideo.jsp");

        return modelAndView;
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Video video) {
        if (video.getId() == null) {
            videoService.addVideo(video);
            System.out.println("添加的video为:" + video);
        } else {
            videoService.updateVideo(video);
            System.out.println("修改的video为:" + video);
        }

        return "redirect:/video/list";
    }

    @RequestMapping("videoDel")
    @ResponseBody
    public String videoDel(Integer id) {
        videoService.videoDel(id);
        System.out.println("删除的id为:" + id);

        return "success";
    }

    @RequestMapping("addVideo")
    public ModelAndView addVideo() {

        Video video = new Video();
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("video", video);
        List<Speaker> speakerList = speakerService.findAll();
        modelAndView.addObject("speakerList", speakerList);
        List<Course> courseList = courseService.findAll();
        modelAndView.addObject("courseList", courseList);

        modelAndView.setViewName("/behind/addVideo.jsp");

        return modelAndView;
    }

    @RequestMapping("delBatchVideos")
    public String delBatchVideos(Integer[] ids) {
        System.out.println(ids);

        for (Integer id : ids) {
            System.out.println("删除id：" + id);
            videoService.videoDel(id);
        }

        return "redirect:/video/list";
    }

    @RequestMapping("showVideo")
    public ModelAndView showVideo(Integer videoId, String subjectName) {
        ModelAndView modelAndView = new ModelAndView();

        Video video = videoService.findVideoById(videoId);
        Course course = video.getCourse();
        Integer courseId = course.getId();
        course = courseService.findById(courseId);

        System.out.println(course);
        System.out.println(video);

        modelAndView.addObject("video", video);
        modelAndView.addObject("course", course);
        modelAndView.addObject("subjectName", subjectName);
        modelAndView.setViewName("/before/section.jsp");

        return modelAndView;
    }

}
