package com.sj.skyblue.entity.post;

/**
 * Created by Sunj on 2018/11/27.
 */

public class ChangeOrderService {


    /**
     * token : 9223370493902909103126a4486fafb40579e171e82f8582b69
     * orderId : DD1542944174048UaUn
     * serviceId : 92233704947468049280532130964d347f7b7cd4d6b9e26d73a
     * orderserviceid : ””
     */

    private String token;
    private String orderId;
    private String serviceId;
    private String orderserviceid;

    private ChangeOrderService(Builder builder) {
        token = builder.token;
        orderId = builder.orderId;
        serviceId = builder.serviceId;
        orderserviceid = builder.orderserviceid;
    }

    public static final class Builder {
        private String token;
        private String orderId;
        private String serviceId;
        private String orderserviceid;

        public Builder() {
        }

        public Builder token(String val) {
            token = val;
            return this;
        }

        public Builder orderId(String val) {
            orderId = val;
            return this;
        }

        public Builder serviceId(String val) {
            serviceId = val;
            return this;
        }

        public Builder orderserviceid(String val) {
            orderserviceid = val;
            return this;
        }

        public ChangeOrderService build() {
            return new ChangeOrderService(this);
        }
    }
}
