package com.sj.skyblue.entity.post;

import java.util.List;

/**
 * Created by Sunj on 2018/11/17.
 */

public class PayBackBean {


    /**
     * token : 9223370495197562446aa5565baf5b847f090e1705a5d4273ea
     * orderId : DD1539826907301AJAX
     * orderNums : [{"orderNum":"T1541661130336dUWQSetO","payStatus":"1","payChannel":"2","payChannelName":"支付宝"}]
     */

    private String token;
    private String orderId;
    private List<OrderNumsBean> orderNums;

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

    public List<OrderNumsBean> getOrderNums() {
        return orderNums;
    }

    public void setOrderNums(List<OrderNumsBean> orderNums) {
        this.orderNums = orderNums;
    }

    public static class OrderNumsBean {
        /**
         * orderNum : T1541661130336dUWQSetO
         * payStatus : 1
         * payChannel : 2
         * payChannelName : 支付宝
         */

        private String orderNum;
        private String payStatus;
        private String payChannel;
        private String payChannelName;

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public String getPayChannel() {
            return payChannel;
        }

        public void setPayChannel(String payChannel) {
            this.payChannel = payChannel;
        }

        public String getPayChannelName() {
            return payChannelName;
        }

        public void setPayChannelName(String payChannelName) {
            this.payChannelName = payChannelName;
        }
    }
}
