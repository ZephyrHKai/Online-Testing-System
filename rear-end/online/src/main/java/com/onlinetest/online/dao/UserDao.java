package com.onlinetest.online.dao;

import com.onlinetest.online.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户操作DAO
 */
public interface UserDao {

    /**
     * 1.通过userId查询用户
     * @param userId
     * @return
     */
    User queryUserById(int userId);

    /**
     * 2.插入用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 3.通过userId删除用户
     * @param userId
     * @return
     */
    int deleteUser(int userId);

    /**
     * 4.通过用户名和密码查询用户，登录用
     * @param username
     * @param userpwd
     * @return
     */
    User queryUserByLogin(@Param("username") String username,
                          @Param("userpwd") String userpwd);

    /**
     * 5.查询所有用户列表，管理员用
     * @return
     */
    List<User> queryUserList();

    /**
     * 6.通过用户名查找
     * @param username
     * @return
     */
    User queryUserByName(String username);

    /**
     * 7.通过用户名来修改用户信息(权限,密码)
     * @param username
     * @return
     */
    int updateUser(User user);
}
