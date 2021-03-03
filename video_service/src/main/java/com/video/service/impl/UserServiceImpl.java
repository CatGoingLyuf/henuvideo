package com.video.service.impl;

import com.video.dao.UserMapper;
import com.video.pojo.User;
import com.video.pojo.UserExample;
import com.video.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lyuf
 * @date: 2020/10/19 19:20
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User loginUser(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andEmailEqualTo(user.getEmail()).andPasswordEqualTo(user.getPassword());

        List<User> checkedUser = userMapper.selectByExample(userExample);

        return checkedUser.get(0);
    }

    @Override
    public User findUserById(Integer account) {
        return userMapper.selectByPrimaryKey(account);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);

    }

    @Override
    public User findUserByEmail(String email) {
        User user = userMapper.findUserByEmail(email);

        return user;
    }

    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }
}
