package com.sj.skyblue.entity.order;

import com.sj.skyblue.entity.order.base.OrderServiceBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunj on 2018/11/15.
 */

public class OrderProductService extends OrderServiceBase {

    private String baseOrderId;

    List<OrderProduct> orderProductList = new ArrayList<>();

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public String getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(String baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

}
