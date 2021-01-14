package com.onlinetest.online.service.Impl;

import com.onlinetest.online.dao.PageDao;
import com.onlinetest.online.dao.ScoreDao;
import com.onlinetest.online.dao.UserDao;
import com.onlinetest.online.dto.Result;
import com.onlinetest.online.dto.ScoreItem;
import com.onlinetest.online.entity.Page;
import com.onlinetest.online.entity.Score;
import com.onlinetest.online.entity.User;
import com.onlinetest.online.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.ServiceMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kevinhuang
 * @date 2020-06-28 19:40
 */
@Service
@Slf4j
public class ScoreServiceImpl  implements ScoreService {
    @Autowired
    private ScoreDao scoreDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PageDao pageDao;

    /**
     * 1.提交成绩
     * @param score
     * @return
     */
    @Override
    @Transactional
    public Result addScoreItem(Score score) throws Exception {
        if(score.getPageId()==null || score.getUserId() == null)
            throw new Exception("成绩信息不能为空！");
        int effectedNum = scoreDao.insertScore(score);
        if(effectedNum <= 0)
            return Result.buildError("成绩插入失败", -1);
        return Result.buildSuccess();
    }

    /**
     * 2.删除成绩
     * @param score
     * @return
     */
    @Override
    public Result deleteScoreItem(Score score) throws Exception {
        if(score.getPageId()==null || score.getUserId() == null)
            throw new Exception("成绩信息不能为空！");

        int effectedNum = scoreDao.deleteScore(score.getScoreId());
        if(effectedNum <= 0)
            return Result.buildError("成绩删除失败", -1);
        return Result.buildSuccess();
    }

    /**
     * 3.根据试卷ID获取成绩列表
     * @return
     */
    @Override
    public Result getScoreList(Integer pageId) {
        List<Score> scoreList = scoreDao.queryScoreListByPageId(pageId);
        List<ScoreItem>  scoreItems = new ArrayList<>();
        if(scoreList != null){
            getScoreItems(scoreList, scoreItems);
        }
        return Result.buildSuccess(scoreItems, scoreItems.size());
    }

    /**
     * 4.根据学生id获取成绩表
     * @param userId
     * @return
     */
    @Override
    public Result getScoreByUserId(int userId) {
        List<Score> scoreList = scoreDao.queryScoreListByUserId(userId);
        List<ScoreItem> scoreItems = new ArrayList<>();
        if(scoreList != null){
            getScoreItems(scoreList, scoreItems);
        }
        return Result.buildSuccess(scoreItems, scoreItems.size());
    }



    /**
     * 5.修改成绩信息
     * @param score
     * @return
     */
    @Override
    public Result updateScore(Score score) throws Exception {
        if(score.getUserId()==null)
            throw new Exception("用户信息为空");

        int effectedNum = scoreDao.updateScore(score);
        if(effectedNum <= 0)
            return Result.buildError("修改失败", -1);
        return Result.buildSuccess();
    }

    /**
     * 6.获取全部学生的成绩
     * @return
     */
    @Override
    public Result getAllScore() {
        List<Score> scoreList = scoreDao.queryScoreList();
        log.info("get all size : " + scoreList.size());
        List<ScoreItem> scoreItems = new ArrayList<>();
        if(scoreList != null){
            getScoreItems(scoreList, scoreItems);
        }
        return Result.buildSuccess(scoreItems, scoreItems.size());
    }

    /**
     * 获取成绩信息
     * @param scoreList
     * @param scoreItems
     */
    private void getScoreItems(List<Score> scoreList, List<ScoreItem> scoreItems) {
        for(Score score : scoreList){
            User user = userDao.queryUserById(score.getUserId());
            Page page = pageDao.queryPageById(score.getPageId());
            ScoreItem scoreItem = getScoreItemFormUser(user,page,score);
            scoreItems.add(scoreItem);
        }
    }



    /**
     * 从用户信息和成绩获取用户成绩实体类
     * @param user
     * @param page
     * @param score
     * @return
     */
    private ScoreItem getScoreItemFormUser(User user, Page page, Score score) {
        ScoreItem scoreItem = new ScoreItem();
        scoreItem.setName(user.getName());
        scoreItem.setTotalScore(score.getTotalScore());
        scoreItem.setUserId(user.getUserId());
        scoreItem.setPageId(page.getPageId());
        scoreItem.setPageName(page.getPageName());

        return scoreItem;
    }
}
