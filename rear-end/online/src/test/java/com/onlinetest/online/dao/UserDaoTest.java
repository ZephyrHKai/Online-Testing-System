package com.onlinetest.online.dao;

import com.onlinetest.online.entity.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试项目 : 用户信息操作测试
 * 测试状态 :
 * 测试时间 : 2020.6.21 13.03
 * 测试人员 : kevinhuang
 *
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    @Order(1)
    void insertUser() {
        User user = new User();
        user.setName("测试用户");
        user.setStatus(0);
        user.setUsername("kevin");
        user.setUserpwd("123");
        int effectedNum = userDao.insertUser(user);
        assertEquals(1, effectedNum);
        System.out.println(user);
    }


    @Test
    @Order(2)
    void queryUserById() {
        User user = userDao.queryUserById(1);
        assertNotEquals(null, user);
        System.out.println(user);
    }


    @Test
    @Order(3)
    void queryUserByLogin() {
        User user = userDao.queryUserByLogin("kevin", "123");
        assertNotEquals(null, user);
        System.out.println(user);
    }

    @Test
    @Order(4)
    void queryUserList() {
        List<User> userList = userDao.queryUserList();
        assertEquals(1, userList.size());
        System.out.println(userList);
    }

//    @Test
//    @Order(5)
    void deleteUser() {

    }
}