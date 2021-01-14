package com.onlinetest.online.dao;

import com.onlinetest.online.entity.Item;

import java.util.List;

/**
 * 试题操作DAO
 */
public interface ItemDao {
    /**
     * 1.新增试题
     * @param item
     * @return
     */
    int insertItem(Item item);

    /**
     * 2.查询试题列表
     * @return
     */
    List<Item> queryItemList();

    /**
     * 3.修改试题
     * @param item
     * @return
     */
    int updateItem(Item item);

    /**
     * 4.通过题目ID来查询题目
     * @param sid
     * @return
     */
    Item queryItemById(int sid);

    /**
     * 5.通过题目ID来删除题目
     * @param sid
     * @return
     */
    int deleteItem(int sid);

    /**
     * 6.计算试题总数
     * @return
     */
    int countItem();
}
