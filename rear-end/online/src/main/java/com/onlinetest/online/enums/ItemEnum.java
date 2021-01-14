package com.onlinetest.online.enums;

/**
 * 试题状态
 */
public enum ItemEnum implements BaseEnum{
    ITEM_NULL(-1001,"试题为空"),SUCCESS(0,"试题操作成功"),ITEM_DELETE(1,"试题删除成功"),
    ITEM_EDIT(2,"试题修改成功"),FAIL(-1002,"操作失败");

    private int state;
    private String statusInfo;

    ItemEnum(int state, String statusInfo) {
        this.state = state;
        this.statusInfo = statusInfo;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public String getStateInfo() {
        return statusInfo;
    }
}
