package com.sj.skyblue.entity;

import java.util.List;

/**
 * Created by Sunj on 2018/12/1.
 */

public class OrderData {


    private String num;
    private String time;
    private List<OrderItem> orderInfo;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<OrderItem> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<OrderItem> orderInfo) {
        this.orderInfo = orderInfo;
    }
}
