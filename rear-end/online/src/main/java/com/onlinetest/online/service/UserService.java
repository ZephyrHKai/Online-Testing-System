package com.onlinetest.online.service;

import com.onlinetest.online.dto.Result;
import com.onlinetest.online.entity.User;

import java.util.List;

/**
 * 用户服务类
 */
public interface UserService {

    /**
     * 1.用户登录
     * @param user
     * @return
     */
    Result userLogin(User user);

    /**
     * 2.获取用户列表1
     * @return
     */
    List<User> getUserList();

    /**
     * 3.新增新用户
     * @param user
     * @return
     */
    Result addNewUser(User user) throws Exception;

    /**
     * 4.修改用户
     * @param user
     * @return
     */
    Result updateUser(User user);

    /**
     * 5.删除用户
     * @param userId
     * @return
     */
    Result deleteUser(Integer userId);
}
