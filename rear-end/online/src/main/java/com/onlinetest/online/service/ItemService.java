package com.onlinetest.online.service;

import com.onlinetest.online.dto.Result;
import com.onlinetest.online.entity.Item;

/**
 * 试题服务接口
 */
public interface ItemService {

    /**
     * 1.新增试题
     * @param item
     * @return
     */
    Result addNewItem(Item item);

    /**
     * 2.查询试题列表
     * @return
     */
    Result queryItemList();

    /**
     * 3.修改试题
     * @param item
     * @return
     */
    Result updateItem(Item item);

    /**
     * 4.删除试题
     * @param sid
     * @return
     */
    Result deleteItem(Integer sid);

    /**
     * 5.根据试卷id获取题目
     * @param pageId
     * @return
     */
    Result getItemByPageId(Integer pageId) throws Exception;

}
