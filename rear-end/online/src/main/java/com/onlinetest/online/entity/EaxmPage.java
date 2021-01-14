package com.onlinetest.online.entity;

import lombok.Data;

/**
 * @author kevinhuang
 * @date 2020-06-20 17:02
 * 学生考试表
 */
@Data
public class EaxmPage {
    Integer index;
    Integer pageId;
    Integer sid;
    String studentKey;
    String sKey;
}
