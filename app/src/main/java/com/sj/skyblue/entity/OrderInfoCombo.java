package com.sj.skyblue.entity;

import java.util.List;

/**
 * Created by Sunj on 2018/12/19.
 */

public class OrderInfoCombo {

    /**
     * price : 20
     * name : 测试优惠1
     * serviceList : [{"price":"500","name":"全家福","id":"9223370494746645919677fbc2b72bd4751a2136af11c1fbdd8"},{"price":"180","name":"证件照","id":"922337049475071040430f73740ec96414fb13d7026ad7f4e70"}]
     */

    private String price;
    private String name;
    private List<ServiceListBean> serviceList;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServiceListBean> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceListBean> serviceList) {
        this.serviceList = serviceList;
    }

    public static class ServiceListBean {
        /**
         * price : 500
         * name : 全家福
         * id : 9223370494746645919677fbc2b72bd4751a2136af11c1fbdd8
         */

        private String price;
        private String name;
        private String id;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
