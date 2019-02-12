package com.sj.skyblue.entity.post;

import com.sj.skyblue.entity.GoodEntity;

import java.util.List;

/**
 * Created by Sunj on 2018/11/17.
 */

public class AddOrderServiceBean {

    /**
     * token : 92233704946356261570ef42f94b9d84ebca02ba6ec12457560
     * orderId : DD1542219824044fbIS
     * serviceId : 922337049473718265352d2fdf575df4bcd80a281f06165bbb7
     * products : [{"id":"92233704947461935614c144b2363b14cc8b607cd09621d3d2a","type":"在售商品","name":"木讷相框（6寸","price":"120","count":"1"},{"id":"9223370494748748750a5dfcb442e3d4cf683fe71dc2efd2ea7","type":"在售商品","name":"木讷相框（6寸","price":"120","count":"2"}]
     */

    private String token;
    private String orderId;
    private String serviceId;
    private List<AddProductBean> products;

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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<AddProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<AddProductBean> products) {
        this.products = products;
    }
}
