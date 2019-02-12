package com.sj.skyblue.event;

/**
 * Created by Sunj on 2018/11/18.
 */

public class OrderServicePriceEvent {
    String id;
    String price;
    String status;
    String count;

    private OrderServicePriceEvent(Builder builder) {
        id = builder.id;
        price = builder.price;
        status = builder.status;
        count = builder.count;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getCount() {
        return count;
    }


    public static final class Builder {
        private String id;
        private String price;
        private String status;
        private String count;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder price(String val) {
            price = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public Builder count(String val) {
            count = val;
            return this;
        }

        public OrderServicePriceEvent build() {
            return new OrderServicePriceEvent(this);
        }
    }
}
