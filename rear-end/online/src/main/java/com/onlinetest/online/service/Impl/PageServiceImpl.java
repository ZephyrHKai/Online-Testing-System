package com.onlinetest.online.service.Impl;

import com.onlinetest.online.dao.ItemDao;
import com.onlinetest.online.dao.PageDao;
import com.onlinetest.online.dao.PageItemDao;
import com.onlinetest.online.dao.ScoreDao;
import com.onlinetest.online.dto.Result;
import com.onlinetest.online.entity.Item;
import com.onlinetest.online.entity.Page;
import com.onlinetest.online.entity.PageItem;
import com.onlinetest.online.entity.Score;
import com.onlinetest.online.enums.PageEnum;
import com.onlinetest.online.service.PageService;
import com.onlinetest.online.util.RandomUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevinhuang
 * @date 2020-06-24 22:03
 * 试卷服务实现类
 */
@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private PageDao pageDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private PageItemDao pageItemDao;
    @Autowired
    private ScoreDao scoreDao;
    /**
     * 1.新增试卷
     * @param page
     * @return
     */
    @Override
    @Transactional
    public Result addNewPage(Page page) throws Exception{
        int effectedNum = pageDao.addPage(page);
        if(effectedNum <= 0)
            return Result.buildError(PageEnum.PAGE_FAIL);
        return Result.buildSuccess(PageEnum.PAGE_ADD);
    }

    /**
     * 2.修改试卷
     * @param page
     * @return
     */
    @Override
    @Transactional
    public Result updatePage(Page page) throws Exception{
        if(page.getPageId() == null)
            return Result.buildError(PageEnum.PAGE_INFO_NULL);
        int effectedNum = pageDao.updatePage(page);
        if(effectedNum <= 0)
            return Result.buildError(PageEnum.PAGE_FAIL);
        return Result.buildSuccess(PageEnum.PAGE_UPDATE);
    }

    /**
     * 3.删除试卷
     * @param pageId
     * @return
     */
    @Override
    @Transactional
    public Result deletePage(Integer pageId) throws Exception{
        if(pageId == null)
            return Result.buildError(PageEnum.PAGE_INFO_NULL);
        Page page = pageDao.queryPageById(pageId);
        if(page == null)
            return Result.buildError("不存在试卷", -1);
        int effectedNum = pageDao.deletePage(pageId);

        if(effectedNum <= 0)
            throw new Exception("试卷删除失败!");
        //删除试卷下的所有与其关联的题目
        if(page.getTotal() > 0 ) {
            effectedNum = pageItemDao.deleteAllPageItem(pageId);
            if(effectedNum <= 0)
                throw new Exception("关联试题删除失败");
        }

        return Result.buildSuccess(PageEnum.PAGE_DELETE);
    }

    /**
     * 4.获取试卷列表
     * @return
     * @throws Exception
     */
    @Override
    public Result queryPageList() throws Exception {
        List<Page> pageList = pageDao.queryPageList();
        if(pageList.isEmpty()){
            return Result.buildError(PageEnum.PAGE_INFO_NULL);
        }
        return Result.buildSuccess(pageList, pageList.size());
    }

    /**
     * 5.自动组卷
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Result addPageAutomatically(Page page) throws Exception {
        if(page.getTotal() == null || page.getPageName() == null){
            return Result.buildError(PageEnum.PAGE_INFO_NULL);
        }
        //获取试题总数
        int totalItem = itemDao.countItem();
        if(totalItem < page.getTotal()){
            return Result.buildError("试题总数不足", -1);
        }

        int effectedNum = pageDao.addPage(page);
        if(effectedNum <= 0 || page.getPageId() == null)
            throw new Exception("试卷插入失败");

        //获取pageId
        int pageId = page.getPageId();
        //获取题目列表
        List<Item> itemList = itemDao.queryItemList();
        //获取随机的索引
        List<Integer> randomList = RandomUtil.getRandomNum(0, totalItem, page.getTotal());
        //试卷-试题映射
        List<PageItem> pageItemList = new ArrayList<>();
        //逐个插入
        for(Integer index : randomList){
            PageItem pageItem = new PageItem();
            pageItem.setPageId(pageId);
            pageItem.setSid(itemList.get(index).getSid());
            pageItemList.add(pageItem);
        }

        effectedNum = pageItemDao.batchInsertPageItem(pageItemList);
        if(effectedNum <= 0)
            throw new Exception("试题试卷映射插入失败");

        return Result.buildSuccess(PageEnum.PAGE_ADD);
    }

    /**
     * 6.获取考试列表
     * @return
     * @throws Exception
     */
    @Override
    public Result queryExamPage() throws Exception {
        List<Page> pageList = pageDao.queryExamList();
        if(pageList.isEmpty()){
            return Result.buildError(PageEnum.PAGE_INFO_NULL);
        }
        return Result.buildSuccess(pageList, pageList.size());
    }

    /**
     * 7.检查是否已经答题
     * @param pageId
     * @param userId
     * @return
     */
    @Override
    public Result checkPage(Integer pageId, Integer userId) {
        if(pageId == null || userId == null)
            return Result.buildError("用户名或试卷不存在!", 1);

        Score score = scoreDao.queryScoreByPageIdAndUserId(pageId, userId);
        if(score != null){
            return Result.buildError("已经答过!", -1);
        }
        return Result.buildSuccess(userId);
    }
}
