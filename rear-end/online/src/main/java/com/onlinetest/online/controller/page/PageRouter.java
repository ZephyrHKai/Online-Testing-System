package com.onlinetest.online.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kevinhuang
 * @date 2020-06-25 09:26
 * 试卷管理页面转发
 */
@Controller
public class PageRouter {

    @GetMapping("/pagemanage")
    public String pageManage(){return "pagemanage";}

    @GetMapping("/addpage")
    public String addPage(){return "pageAdd";}
}
