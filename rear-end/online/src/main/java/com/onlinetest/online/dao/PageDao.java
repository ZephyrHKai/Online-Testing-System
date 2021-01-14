package com.onlinetest.online.dao;

import com.onlinetest.online.entity.Page;

import java.util.List;

/**
 * 试卷数据操作接口
 */
public interface PageDao{

    /**
     * 1.新增试卷
     * @param page
     * @return
     */
    int addPage(Page page);

    /**
     * 2.修改试卷
     * @param page
     * @return
     */
    int updatePage(Page page);

    /**
     * 3.通过pageId来删除试卷
     *
     * @param pageId
     * @return
     */
    int deletePage(int pageId);

    /**
     * 4.获取试卷列表
     * @return
     */
    List<Page> queryPageList();

    /**
     * 5.获取考试试卷
     * @return
     */
    List<Page> queryExamList();

    /**
     * 6.根据pageid获取试卷
     * @param pageId
     * @return
     */
    Page queryPageById(int pageId);
}
