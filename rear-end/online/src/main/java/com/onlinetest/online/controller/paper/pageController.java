package com.onlinetest.online.controller.paper;

import com.onlinetest.online.dto.Result;
import com.onlinetest.online.entity.Page;
import com.onlinetest.online.service.ItemService;
import com.onlinetest.online.service.PageService;
import com.onlinetest.online.util.HttpServletRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Http2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kevinhuang
 * @date 2020-06-25 00:31
 */
@RestController
@RequestMapping("/page")
@CrossOrigin(origins = "*")
@Slf4j
public class pageController {
    @Autowired
    private PageService pageService;
    @Autowired
    private ItemService itemService;
    /**
     * 1.获取试卷列表
     * @return
     * @throws Exception
     */
    @GetMapping("/getpagelist")
    public Result getPageList() throws Exception {
        log.info("queryPageList....");
        return pageService.queryPageList();
    }

    /**
     * 2.自动新增试卷
  * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/addnewpage")
    public Result addNewPage(HttpServletRequest request) throws Exception{
        Page page = getPageFormRequest(request);
        return pageService.addPageAutomatically(page);
    }

    /**
     * 3.修改试卷
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/updatepage")
    public Result updatePage(HttpServletRequest request) throws Exception{
        Integer pageId = HttpServletRequestUtil.getInt(request, "pageId");
        Page page = getPageFormRequest(request);
        page.setPageId(pageId);
        log.info("update page....");
        return pageService.updatePage(page);
    }

    /**
     * 4.删除试卷
     * @param request
     * @return
     */
    @PostMapping("/deletepage")
    public Result deletePage(HttpServletRequest request) throws Exception{
        Integer pageId = HttpServletRequestUtil.getInt(request, "pageId");
        return pageService.deletePage(pageId);
    }

    /**
     * 5.根据试卷id获取题目
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping("/getitemlistbyid")
    public Result getItemByPageId(HttpServletRequest request) throws Exception {
        Integer pageId = HttpServletRequestUtil.getInt(request, "pageId");
        log.info("get page!");
        return itemService.getItemByPageId(pageId);
    }

    /**
     * 6.检查是否已经答过题
     * @param request
     * @return
     */
    @GetMapping("/checkpage")
    public Result checkPageStatus(HttpServletRequest request){
        Integer pageId = HttpServletRequestUtil.getInt(request, "pageId");
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        return pageService.checkPage(pageId, userId);
    }

    @GetMapping("/getexampage")
    public Result getExamPage(HttpServletRequest request) throws Exception {
        log.info("get examPage: ....");
        return pageService.queryExamPage();
    }

    /**
     * 从请求中获取试卷信息
     * @param request
     * @return
     */
    private Page getPageFormRequest(HttpServletRequest request){
        String pageName = HttpServletRequestUtil.getString(request, "pageName");
        Integer total = HttpServletRequestUtil.getInt(request, "total");
        Integer status = HttpServletRequestUtil.getInt(request, "status");

        Page page = new Page();
        if(pageName != null)
            page.setPageName(pageName);
        if(status != null)
            page.setStatus(status);
        if(total != null)
            page.setTotal(total);
        return page;
    }

}
