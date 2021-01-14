package com.onlinetest.online.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kevinhuang
 * @date 2020-06-24 15:36
 */
@Controller
public class ItemRouter {
    @GetMapping("/itemmange")
    public String itemMange(){ return "itemmanage";}

    @GetMapping("/edititem")
    public String itemEdit(){ return "itemEdit";}

    @GetMapping("/additem")
    public String itemAdd(){ return "itemAdd";}

}
