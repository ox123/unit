package org.dubbo.basic.demo;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private T data;
    private boolean isSuccess;
    private boolean msg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
