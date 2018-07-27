package com.johnny.atcrowdfunding.util;

/**
 * @author johnny
 * @create 2018-07-18 下午4:11
 **/
public class AjaxResult {

    private boolean success;

    private Object data;

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}