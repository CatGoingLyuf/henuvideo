package com.video.service;

import com.video.pojo.Speaker;
import com.video.pojo.Video;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 21:46
 */
public interface SpeakerService {
    List<Speaker> findAll();

    Speaker findById(Integer id);

    void addSpeaker(Speaker speaker);

    void updateSpaker(Speaker speaker);

    void speakerDel(Integer id);
}
