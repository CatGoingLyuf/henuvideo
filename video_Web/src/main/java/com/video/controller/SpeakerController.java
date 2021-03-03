package com.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.video.pojo.Speaker;
import com.video.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/20 10:08
 */

@Controller
@RequestMapping("speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @RequestMapping("showSpeakerList")
    public ModelAndView showSpeakerList(@RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        ModelAndView modelAndView = new ModelAndView();

        PageHelper.startPage(pageNum, 10);
        List<Speaker> speakerList = speakerService.findAll();
        PageInfo<Speaker> speakerPageInfo = new PageInfo<>(speakerList);

        modelAndView.addObject("pageInfo", speakerPageInfo);
        System.out.println(speakerList);
        modelAndView.setViewName("/behind/speakerList.jsp");
        return modelAndView;
    }

    @RequestMapping("edit")
    public ModelAndView edit(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Speaker byId = speakerService.findById(id);
        modelAndView.addObject("speaker", byId);

        modelAndView.setViewName("/behind/addSpeaker.jsp");

        return modelAndView;
    }

    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Speaker speaker) {
        if (speaker.getId() == null) {
            speakerService.addSpeaker(speaker);
            System.out.println("添加的speaker为:" + speaker.getSpeakerName());
        } else {
            speakerService.updateSpaker(speaker);
            System.out.println("修改的speaker为:" + speaker.getSpeakerName());
        }

        return "redirect:/speaker/showSpeakerList";
    }

    @RequestMapping("addSpeaker")
    public ModelAndView addSpeaker() {

        Speaker speaker = new Speaker();
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("speaker", speaker);

        modelAndView.setViewName("/behind/addSpeaker.jsp");

        return modelAndView;
    }

    @RequestMapping("speakerDel")
    @ResponseBody
    public String speakerDel(Integer id) {

        speakerService.speakerDel(id);

        System.out.println("删除的id为:" + id);

        return "success";
    }
}
