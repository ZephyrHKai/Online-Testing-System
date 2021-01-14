package com.onlinetest.online.dao;

import com.onlinetest.online.entity.PageItem;

import java.util.List;

/**
 * 试卷-试题关联表
 */
public interface PageItemDao {

    /**
     *1.插入一条关联记录
     * @param pageItem
     * @return
     */
    int insertPageItem(PageItem pageItem);

    /**
     * 2.删除一条关联记录
     * @param pageItem
     * @return
     */
    int deletePageItem(PageItem pageItem);

    /**
     * 3.批量插入试卷-试题信息
     * @param pageItemList
     * @return
     */
    int batchInsertPageItem(List<PageItem> pageItemList);

    /**
     * 4.删除该试卷下的所有试题
     * @param pageId
     * @return
     */
    int deleteAllPageItem(int pageId);

    /**
     * 5.根据页面id批量获取记录
     * @return
     */
    List<PageItem> queryPageItemList(int pageId);
}
