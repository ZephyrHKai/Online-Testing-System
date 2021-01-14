package com.onlinetest.online.service.Impl;

import com.onlinetest.online.dao.ItemDao;
import com.onlinetest.online.dao.PageItemDao;
import com.onlinetest.online.dto.Result;
import com.onlinetest.online.entity.Item;
import com.onlinetest.online.entity.PageItem;
import com.onlinetest.online.enums.ItemEnum;
import com.onlinetest.online.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevinhuang
 * @date 2020-06-24 11:14
 * 试题服务实现类
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private PageItemDao pageItemDao;

    /**
     * 1.新增试题
     * @param item
     * @return
     */
    @Override
    public Result addNewItem(Item item) {
        int effectedNum = itemDao.insertItem(item);
        if(effectedNum <= 0)
            return Result.buildSuccess(ItemEnum.FAIL);
        return Result.buildSuccess(ItemEnum.SUCCESS);
    }

    /**
     * 2.查询试题列表
     * @return
     */
    @Override
    public Result queryItemList() {
        List<Item> itemList = itemDao.queryItemList();
        if (itemList == null || itemList.isEmpty()) {
            return Result.buildError("列表为空", -1);
        }
        return Result.buildSuccess(itemList, itemList.size());
    }

    /**
     * 3.修改试题
     * @param item
     * @return
     */
    @Override
    @Transactional
    public Result updateItem(Item item) {
        if(item.getSid() == null) {
            return Result.buildError(ItemEnum.ITEM_NULL);
        }
        int effectedNum = itemDao.updateItem(item);
        if(effectedNum <= 0 )
            return Result.buildError(ItemEnum.FAIL);
        return Result.buildSuccess(ItemEnum.ITEM_EDIT);
    }

    /**
     * 4.删除试题
     * @param sid
     * @return
     */
    @Override
    @Transactional
    public Result deleteItem(Integer sid) {
        if(sid == null)
            return Result.buildError(ItemEnum.ITEM_NULL);
        Item item = itemDao.queryItemById(sid);
        if(item == null){
            return Result.buildError(ItemEnum.ITEM_NULL);
        }
        int effectedNum = itemDao.deleteItem(sid);
        if(effectedNum <= 0)
            return Result.buildError(ItemEnum.FAIL);
        return Result.buildSuccess(ItemEnum.ITEM_DELETE);
    }

    /**
     * 5.根据试卷id获取题目
     * @param pageId
     * @return
     */
    @Override
    @Transactional
    public Result getItemByPageId(Integer pageId) throws Exception {
        if(pageId == null)
            throw new Exception("不存在试卷");
        List<PageItem> pageItemList = pageItemDao.queryPageItemList(pageId);

        if(pageItemList.isEmpty())
            throw new Exception("题目为空");

        List<Item> itemList = new ArrayList<>();

        for (PageItem pageItem : pageItemList) {
            Item item = itemDao.queryItemById(pageItem.getSid());
            if(item!=null)
                itemList.add(item);
        }

        return Result.buildSuccess(itemList, itemList.size());

    }
}
