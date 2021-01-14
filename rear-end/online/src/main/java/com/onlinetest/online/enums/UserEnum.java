package com.onlinetest.online.enums;

public enum UserEnum implements BaseEnum {
    LOGINFAIL(-1,"账号或密码输入错误"),SUCCESS(0,"操作成功"),NULL_INFO(-1006,"注册信息为空"),
    ONLY_ONE_USER(-1007,"用户已经存在"),STLOGIN(1,"学生登录"),MAMNAGELOGIN(2,"管理员登录");

    private int state;
    private String stateInfo;

    UserEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public String getStateInfo() {
        return stateInfo;
    }
}
