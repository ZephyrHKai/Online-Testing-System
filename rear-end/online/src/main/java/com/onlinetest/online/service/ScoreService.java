package com.onlinetest.online.service;

import com.onlinetest.online.dto.Result;
import com.onlinetest.online.entity.Score;

public interface ScoreService {

    /**
     * 1.提交成绩
     * @param score
     * @return
     */
    Result addScoreItem(Score score) throws Exception;


    /**
     * 2.删除成绩
     * @param score
     * @return
     */
    Result deleteScoreItem(Score score) throws Exception;


    /**
     * 3.根据试卷ID获取成绩列表,老师用
     * @return
     */
    Result getScoreList(Integer pageId);

    /**
     * 4.根据学生id获取成绩
     * @param userId
     * @return
     */
    Result getScoreByUserId(int userId);

    /**
     * 5.修改学生成绩信息
     * @param score
     * @return
     */
    Result updateScore(Score score) throws Exception;

    /**
     * 6.获取全部学生的成绩
     * @return
     */
    Result getAllScore();

}
