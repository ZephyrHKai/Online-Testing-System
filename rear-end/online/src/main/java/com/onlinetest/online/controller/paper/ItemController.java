package com.onlinetest.online.controller.paper;

import com.onlinetest.online.dto.Result;
import com.onlinetest.online.entity.Item;
import com.onlinetest.online.service.ItemService;
import com.onlinetest.online.util.HttpServletRequestUtil;
import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kevinhuang
 * @date 2020-06-24 15:05
 * 试题管理
 */
@RestController
@RequestMapping("/item")
@Slf4j
@CrossOrigin(origins = "*")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 1.新增试题
     * @param request
     * @return
     */
    @PostMapping("/addnewitem")
    public Result addNewItems(HttpServletRequest request){
        Item item = getItemFromRequest(request);
        return itemService.addNewItem(item);
    }

    /**
     * 2.获取题目列表
     * @param request
     * @return
     */
    @GetMapping("/queryitemlist")
    public Result queryItemList(HttpServletRequest request){
        return itemService.queryItemList();
    }

    /**
     * 3.修改试题
     * @param request
     * @return
     */
    @PostMapping("/updateitem")
    public Result updateItem(HttpServletRequest request){
        Item item = getItemFromRequest(request);
        Integer sid = HttpServletRequestUtil.getInt(request, "sid");
        log.info("sid: " + sid);
        if(sid == null || sid < 0)
            return Result.buildError("sid为空!", -1);
        item.setSid(sid);
        return itemService.updateItem(item);
    }

    /**
     * 4.删除试题
     * @param request
     * @return
     */
    @PostMapping("/deleteitem")
    public Result deleteItem(HttpServletRequest request){
        Integer sid = HttpServletRequestUtil.getInt(request, "sid");
        return itemService.deleteItem(sid);
    }


    /**
     * 从请求中获取试题实体类
     * @param request
     * @return
     */
    private Item getItemFromRequest(HttpServletRequest request) {

        String scontent = HttpServletRequestUtil.getString(request, "scontent");
        String sa = HttpServletRequestUtil.getString(request, "sa");
        String sb = HttpServletRequestUtil.getString(request, "sb");
        String sc = HttpServletRequestUtil.getString(request, "sc");
        String sd = HttpServletRequestUtil.getString(request, "sd");
        String ans = HttpServletRequestUtil.getString(request, "ans");

        Item item = new Item();
        item.setScontent(scontent);
        item.setSa(sa);
        item.setSb(sb);
        item.setSc(sc);
        item.setSd(sd);
        item.setAns(ans);

        return item;
    }


}
