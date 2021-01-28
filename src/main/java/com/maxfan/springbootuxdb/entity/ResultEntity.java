package com.maxfan.springbootuxdb.entity;

public class ResultEntity {
    public final static int SUCCESS=200;
    public final static int FAILURE=300;
    public final static int EXCEPTION=500;
    private int code;
    private String msg;
    private Object data;
    public ResultEntity(){
        this.code= SUCCESS;
        this.msg="执行成功!";
    }
    public ResultEntity(Object data){
        this.code= SUCCESS;
        this.msg="执行成功!";
        this.data=data;
    }
    public ResultEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResultEntity(String msg, Object data){
        this.code= SUCCESS;
        this.msg=msg;
        this.data=data;
    }
    public ResultEntity(int code, String msg, Object data){
        this.code= code;
        this.msg=msg;
        this.data=data;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
