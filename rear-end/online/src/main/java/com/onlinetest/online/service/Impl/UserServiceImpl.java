package com.onlinetest.online.service.Impl;

import com.alibaba.fastjson.JSON;
import com.onlinetest.online.dao.UserDao;
import com.onlinetest.online.dto.Result;
import com.onlinetest.online.entity.User;
import com.onlinetest.online.enums.UserEnum;
import com.onlinetest.online.service.RedisService;
import com.onlinetest.online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kevinhuang
 * @date 2020-06-22 11:23
 * 用户服务实现类
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;

    /**
     * 1.用户登录
     * @param user
     * @return
     */
    @Override
    public Result userLogin(User user) {
        //判空
        if(user.getUsername() == null || user.getUserpwd()==null)
            return Result.buildError(UserEnum.LOGINFAIL);

        User loginUser = userDao.queryUserByLogin(user.getUsername(), user.getUserpwd());
        //用户不存在
        if(loginUser == null)
            return Result.buildError(UserEnum.NULL_INFO);

        redisService.set("user" + loginUser.getUserId(), JSON.toJSONString(loginUser));
        if(loginUser.getStatus() == 0)
            return Result.buildSuccess(UserEnum.STLOGIN,loginUser);
        return Result.buildSuccess(UserEnum.MAMNAGELOGIN, loginUser);
    }

    /**
     * 2.获取用户列表
     * @return
     */
    @Override
    public List<User> getUserList() {
        return userDao.queryUserList();
    }

    /**
     * 3.新增新用户
     * @param user
     * @return
     */
    @Override
    @Transactional
    public Result addNewUser(User user) throws Exception {
        User checkUser = userDao.queryUserByName(user.getUsername());
        //用户名已经存在
        if(checkUser != null){
            return Result.buildError(UserEnum.ONLY_ONE_USER);
        }
        int effectedNum = userDao.insertUser(user);
        if(effectedNum <= 0)
            throw new Exception("新增失败");
        return Result.buildSuccess(UserEnum.SUCCESS);
    }

    /**
     * 4.修改用户
     * @param user
     * @return
     */
    @Override
    public Result updateUser(User user) {
        User localUser = userDao.queryUserByName(user.getUsername());
        if(localUser == null){
            return Result.buildError(UserEnum.NULL_INFO);
        }
        int effectedNum = userDao.updateUser(user);
        if(effectedNum <= 0){
            return Result.buildError("修改失败",-1);
        }
        return Result.buildSuccess(UserEnum.SUCCESS);
    }

    /**
     * 5.删除用户
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public Result deleteUser(Integer userId){
        int effectedNum = userDao.deleteUser(userId);
        if(effectedNum <= 0)
            return Result.buildError("删除失败!", -1);
        return Result.buildSuccess(UserEnum.SUCCESS);
    }
}
