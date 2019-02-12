package com.sj.skyblue.http.base;

import android.support.annotation.Keep;

/**
 * 创建时间: on 2018/3/31.
 * 创建人: 孙杰
 * 功能描述:返回数据基础类
 */
@Keep
public class BasePayResponse<T> {

    String result_code;
    T biz_response;
    String error_code;
    String error_message;

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public T getBiz_response() {
        return biz_response;
    }

    public void setBiz_response(T biz_response) {
        this.biz_response = biz_response;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
