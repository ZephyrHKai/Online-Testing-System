package com.onlinetest.online.dto;

import com.onlinetest.online.enums.BaseEnum;
import lombok.Data;

/**
 * @author kevinhuang
 * @date 2020-06-21 17:12
 * 统一返回结果
 */
@Data
public class Result {
    private boolean success; //请求是否成功
    private Object data; //成功返回的数据
    private String msg; //信息
    private int code; //状态码
    private int count; //数据个数
    public Result(){

    }
    public Result(boolean success){
        this.success = success;
    }

    public Result(boolean success, Object data){
        this.success = success;
        this.data = data;
        this.count = 1;
    }
    public Result(boolean success, Object data,int count){
        this.success = success;
        this.data = data;
        this.count = count;
    }

    public Result(boolean success, Object data, String msg, int code){
        this.success = success;
        this.data = data;
        this.code = code;
        this.msg = msg;
    }


    public Result(boolean success, int code, String msg) {
        this.success = success;
        this.msg = msg;
        this.code = code;
    }
    //成功时的构造器
    public static Result buildSuccess(Object data) {
        return new Result(true, data);
    }
    public static Result buildSuccess(Object data, int count ) {
        return new Result(true, data, count);
    }

    public static Result buildSuccess(BaseEnum baseEnum){
        return new Result(true, null);
    }
    public static Result buildSuccess(){
        return new Result(true);
    }
    public static Result buildSuccess(BaseEnum baseEnum, Object data){
        return new Result(true, data, baseEnum.getStateInfo(), baseEnum.getState());
    }
    //失败时的构造器
    public static Result buildError(String errmsg, int errCode) {
        return new Result(false, errCode, errmsg);
    }
    //枚举类构造
    public static Result buildError(BaseEnum baseEnum) {
        return new Result(false, baseEnum.getState(), baseEnum.getStateInfo());
    }
}
