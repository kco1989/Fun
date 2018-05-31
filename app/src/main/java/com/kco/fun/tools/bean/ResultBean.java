package com.kco.fun.tools.bean;

/**
 * Created by 666666 on 2018/5/30.
 */
public class ResultBean<T> {
    private int ret;
    private T data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
