package com.onlinetest.online.dao;

import com.onlinetest.online.entity.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生成绩表
 */
public interface ScoreDao {

    /**
     * 1.插入一个成绩记录
     * @param score
     * @return
     */
    int insertScore(Score score);

    /**
     * 2.更新记录
     * @param score
     * @return
     */
    int updateScore(Score score);

    /**
     * 3.删除记录
     * @param scoreId
     * @return
     */
    int deleteScore(int scoreId);

    /**
     * 4.查询成绩列表
     * @return
     */
    List<Score> queryScoreList();

    /**
     * 5.通过试卷id查询成绩列表
     * @param pageId
     * @return
     */
    List<Score> queryScoreListByPageId(int pageId);

    /**
     * 6.通过用户成绩来查询成绩列表
     * @param userId
     * @return
     */
    List<Score> queryScoreListByUserId(int userId);


    /**
     * 7.通过用户Id和试卷ID来查询成绩
     * @param pageId
     * @param userId
     * @return
     */
    Score queryScoreByPageIdAndUserId(@Param("pageId") int pageId,
                                      @Param("userId") int userId);


}
