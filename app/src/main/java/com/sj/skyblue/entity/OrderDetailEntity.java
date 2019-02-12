package com.sj.skyblue.entity;

import com.sj.skyblue.entity.order.OrderInfo;
import com.sj.skyblue.entity.order.OrderProduct;
import com.sj.skyblue.entity.order.OrderService;
import com.sj.skyblue.entity.order.Vip;

import java.util.List;

/**
 * Created by Sunj on 2018/11/12.
 */

public class OrderDetailEntity {

    private OrderInfo orderInfo;
    private Vip vip;
    private List<OrderService> services;
    private List<OrderProduct> products;
    private List<OrderCombo> combo;

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    public List<OrderService> getServices() {
        return services;
    }

    public void setServices(List<OrderService> services) {
        this.services = services;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    public List<OrderCombo> getCombo() {
        return combo;
    }

    public void setCombo(List<OrderCombo> combo) {
        this.combo = combo;
    }
}
