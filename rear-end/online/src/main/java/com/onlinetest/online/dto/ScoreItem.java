package com.onlinetest.online.dto;

import com.onlinetest.online.entity.Score;
import com.onlinetest.online.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author kevinhuang
 * @date 2020-06-28 22:07
 * 个人学生成绩表
 */
@Data
public class ScoreItem {
   Integer pageId;
   String pageName;
   String name;
   Integer userId;
   Double totalScore;
}
