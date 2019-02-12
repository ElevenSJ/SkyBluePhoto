package com.sj.skyblue.entity.post;

/**
 * Created by Sunj on 2018/11/17.
 */

public class GetTransationIdBean {
    String token;
    String orderId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
