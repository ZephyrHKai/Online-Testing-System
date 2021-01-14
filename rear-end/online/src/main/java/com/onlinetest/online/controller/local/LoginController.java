package com.onlinetest.online.controller.local;

import com.onlinetest.online.dto.Result;
import com.onlinetest.online.entity.User;
import com.onlinetest.online.enums.UserEnum;
import com.onlinetest.online.service.RedisService;
import com.onlinetest.online.service.UserService;
import com.onlinetest.online.util.HttpServletRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author kevinhuang
 * @date 2020-06-22 12:11
 */
@RestController
@RequestMapping("/local")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    /**
     * 1.登录
     * @return
     */
    @PostMapping("/login")
    public Result loginCheck(HttpServletRequest request){
        String username = HttpServletRequestUtil.getString(request,"username");
        String userpwd = HttpServletRequestUtil.getString(request, "userpwd");
        User user = new User();
        user.setUsername(username);
        user.setUserpwd(userpwd);
        Result result = userService.userLogin(user);
        user = (User)result.getData();
        log.info("user : " + user);
        request.getSession().setAttribute("userId", user.getUserId());
        request.getSession().setAttribute("status", user.getStatus());
        if(result.getCode() == UserEnum.STLOGIN.getState()){
            //TODO 学生登录
        }
        return result;
    }

    /**
     * 2.登出
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public Result logout(HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("userId"); //获取userId
        redisService.remove("user" + userId); //缓存取消
        Enumeration em = request.getSession().getAttributeNames();
        //清空session
        while (em.hasMoreElements()) {
            request.removeAttribute(em.nextElement().toString());
        }
        log.info("user logout..., userId : " + userId);
        return Result.buildSuccess();
    }
}
