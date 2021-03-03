package com.video.service;

import com.video.pojo.Admin;

import java.util.List;

/**
 * @author lyuf
 * @date 2020/10/19 15:24
 */
public interface AdminService {

    List<Admin> findUserByNameAndPsw(String username, String password);
}
