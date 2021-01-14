package com.onlinetest.online.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kevinhuang
 * @date 2020-06-22 14:02
 * 主页分发
 */
@Controller
public class UserRouter {

    @GetMapping("/index")
    public String Index(){
        return "index";
    }
}
