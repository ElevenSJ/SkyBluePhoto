package com.sj.skyblue.entity;

import java.util.List;

/**
 * Created by Sunj on 2018/11/13.
 */

public class GoodServiceEntity {

    private List<ServiceEntity> services;
    private List<GoodEntity> products;

    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }

    public List<GoodEntity> getProducts() {
        return products;
    }

    public void setProducts(List<GoodEntity> products) {
        this.products = products;
    }
}
