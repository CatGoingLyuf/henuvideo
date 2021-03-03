package com.video.service.impl;

import com.video.dao.VideoMapper;
import com.video.pojo.QueryVo;
import com.video.pojo.Video;
import com.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 19:53
 */
@Service
public class ViseoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> findAllVideo(QueryVo queryVo) {
        return videoMapper.findAllVideo(queryVo);
    }

    @Override
    public Video findById(Integer id) {
        return videoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateVideo(Video video) {
        videoMapper.updateByPrimaryKeyWithBLOBs(video);
    }

    @Override
    public void videoDel(Integer id) {
        videoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addVideo(Video video) {
        videoMapper.insert(video);
    }

    @Override
    public Video findVideoById(Integer videoId) {
        return videoMapper.findVideoById(videoId);
    }
}
