package com.onlinetest.online.enums;

/**
 * 试卷状态
 */
public enum PageEnum implements BaseEnum {
    PAGE_INFO_NULL(-1000,"试卷信息为空"),PAGE_FAIL(-1001,"操作错误"),
    PAGE_ONLY_ONE(-1002,"试卷已经存在"),PAGE_SUCCESS(0,"操作成功"),
    PAGE_ADD(1,"新增成功"),PAGE_UPDATE(2,"更新成功"),PAGE_DELETE(3,"删除成功");
    private int state;
    private String stateInfo;

    PageEnum(int state, String stateInfo) {
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
