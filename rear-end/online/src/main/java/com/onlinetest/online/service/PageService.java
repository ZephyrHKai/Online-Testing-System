package com.onlinetest.online.service;

import com.onlinetest.online.dto.Result;
import com.onlinetest.online.entity.Item;
import com.onlinetest.online.entity.Page;

import java.util.List;

/**
 * 试卷服务接口
 */
public interface PageService {

    /**
     * 1.新增试卷
     * @param page
     * @return
     */
    Result addNewPage(Page page) throws Exception;

    /**
     * 2.修改试卷
     * @param page
     * @return
     */
    Result updatePage(Page page) throws Exception;

    /**
     * 3.删除试卷
     * @param pageId
     * @return
     */
    Result deletePage(Integer pageId) throws Exception;

    /**
     * 4.获取试卷列表
     * @return
     * @throws Exception
     */
    Result queryPageList() throws Exception;

    /**
     * 5.自动组卷
     * @param page
     * @return
     * @throws Exception
     */
    Result addPageAutomatically(Page page) throws Exception;

    /**
     * 6.获取考试列表
     * @return
     * @throws Exception
     */
    Result queryExamPage() throws Exception;

    /**
     * 7.检查是否已经答题
     * @param pageId
     * @param userId
     * @return
     */
    Result checkPage(Integer pageId, Integer userId);


}
