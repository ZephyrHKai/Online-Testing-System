package com.onlinetest.online.entity;

import lombok.Data;

/**
 * @author kevinhuang
 * @date 2020-06-20 16:57
 */
@Data
public class User {
    Integer userId;
    Integer status;
    String username;
    String userpwd;
    String name;
}
