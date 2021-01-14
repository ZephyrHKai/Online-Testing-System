package com.onlinetest.online.entity;

import lombok.Data;

/**
 * @author kevinhuang
 * @date 2020-06-20 17:01
 * 试卷表
 */
@Data
public class Page {
    Integer pageId;
    String pageName;
    Integer status; //试卷状态 0-不可用，1-可用
    Integer total; //试题数量
}
