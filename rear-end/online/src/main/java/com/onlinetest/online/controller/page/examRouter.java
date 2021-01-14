package com.onlinetest.online.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kevinhuang
 * @date 2020-06-26 17:16
 */
@Controller
public class examRouter {
    @GetMapping("/exammanage")
    public String examManage(){return "exammanage";}

    @GetMapping("/stexam")
    public String studentExamPage(){return "stexam";}

    @GetMapping("/stscore")
    public String studentScore(){return "/stscore";}

    @GetMapping("/scorelookup")
    public String scorelookup(){return "/scorelookup";}
}
