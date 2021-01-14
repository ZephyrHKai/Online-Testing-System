package com.onlinetest.online.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kevinhuang
 * @date 2020-06-22 17:55
 */
@Controller
public class ManageRouter {

    @GetMapping("/manage")
    public String manageindex(){
        return "manage";
    }

    @GetMapping("/usermanage")
    public String getusermanage(){return "usermanage";}

    @GetMapping("/adduser")
    public String adduser(){return "userAdd";}

    @GetMapping("/edituser")
    public String editUser(){ return "userEdit";}
}
