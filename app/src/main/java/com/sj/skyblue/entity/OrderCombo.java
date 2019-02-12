package com.sj.skyblue.entity;

import java.util.List;

/**
 * Created by Sunj on 2018/12/19.
 */

public class OrderCombo {

    /**
     * serviceCombo : [{"name":"全家福","id":"9223370494746645919677fbc2b72bd4751a2136af11c1fbdd8"},{"name":"证件照","id":"922337049475071040430f73740ec96414fb13d7026ad7f4e70"}]
     * price : 20
     * name : 测试优惠1
     * id : 9223370491829023691c0b9750172f0419f9b75ad6b9a11336a
     */

    private String price;
    private String name;
    private String id;
    private List<ServiceComboBean> serviceCombo;
    private boolean isSelected = false;

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

    public List<ServiceComboBean> getServiceCombo() {
        return serviceCombo;
    }

    public void setServiceCombo(List<ServiceComboBean> serviceCombo) {
        this.serviceCombo = serviceCombo;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public static class ServiceComboBean {
        /**
         * name : 全家福
         * id : 9223370494746645919677fbc2b72bd4751a2136af11c1fbdd8
         */

        private String name;
        private String id;

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
