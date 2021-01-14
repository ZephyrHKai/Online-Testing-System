package com.onlinetest.online.controller.local;

import com.onlinetest.online.dto.Result;
import com.onlinetest.online.entity.User;
import com.onlinetest.online.service.UserService;
import com.onlinetest.online.util.HttpServletRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author kevinhuang
 * @date 2020-06-23 00:10
 * 管理员控制类
 */
@RestController
@RequestMapping("/manage")
@Slf4j
public class ManageController {
    @Autowired
    private UserService userService;

    /**
     * 1.获取用户列表
     * @return
     */
    @RequestMapping("/getuserlist")
    public Result getUserList(HttpServletRequest request){
        List<User> userList = userService.getUserList();
        if(userList.isEmpty()){
            return Result.buildError("列表为空", -1);
        }
        return Result.buildSuccess(userList, userList.size());
    }

    /**
     * 2.新增用户
     * @param request
     * @return
     */
    @PostMapping("/addnewuser")
    public Result addNewUser(HttpServletRequest request) throws Exception {
        User user = getUserFormRequest(request);
        log.info("add user completed...");
        return userService.addNewUser(user);
    }


    /**
     * 3.更新用户信息
     * @param request
     * @return
     */
    @PostMapping("/updateuser")
    public Result updateUser(HttpServletRequest request){
        User user = getUserFormRequest(request);
        log.info("update completed...");
        return userService.updateUser(user);
    }

    /**
     * 4.删除用户
     * @param request
     * @return
     */
    @PostMapping("/deleteuser")
    public Result deleteUser(HttpServletRequest request){
        Integer userId = HttpServletRequestUtil.getInt(request, "userId");
        if(userId == request.getSession().getAttribute("userId")){
            return Result.buildError("不能删除当前用户!",-1);
        }
        log.info("delete completed...");
        return userService.deleteUser(userId);
    }

    //从httprequest中获取用户信息
    private User getUserFormRequest(HttpServletRequest request) {
        String username = HttpServletRequestUtil.getString(request, "username");
        String userpwd = HttpServletRequestUtil.getString(request, "userpwd");
        Integer status = HttpServletRequestUtil.getInt(request, "status");
        String name = HttpServletRequestUtil.getString(request, "name");

        //创建新用户类
        User user = new User();
        user.setUsername(username);
        user.setUserpwd(userpwd);
        user.setName(name);
        user.setStatus(status);
        return user;
    }


}
