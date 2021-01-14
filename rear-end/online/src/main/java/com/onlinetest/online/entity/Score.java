package com.onlinetest.online.entity;

import lombok.Data;

/**
 * @author kevinhuang
 * @date 2020-06-20 17:05
 * 学生成绩表
 */
@Data
public class Score {
    Integer scoreId;
    Integer userId;
    Integer pageId;
    Double totalScore;
    Integer status;
}
