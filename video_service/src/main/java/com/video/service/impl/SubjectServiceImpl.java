package com.video.service.impl;

import com.video.dao.SubjectMapper;
import com.video.pojo.Subject;
import com.video.pojo.SubjectExample;
import com.video.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lyuf
 * @date: 2020/10/19 16:07
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public List<Subject> findAll() {
        return subjectMapper.selectByExample(null);
    }

    @Override
    public Subject findBySubjectId(Integer subjectId) {
        return subjectMapper.findBySubjectId(subjectId);
    }

    @Override
    public Subject findBySubjectName(String subjectName) {
        SubjectExample subjectExample = new SubjectExample();
        subjectExample.createCriteria().andSubjectNameEqualTo(subjectName);

        return subjectMapper.selectByExample(subjectExample).get(0);
    }


}
