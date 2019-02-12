package com.sj.skyblue.event;

/**
 * Created by Sunj on 2018/12/4.
 */

public class OrderPayRefundEvent {
    public String id;
    public int type;
    public String price;
    public int count;
    public String payChannel;

    public int getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    private OrderPayRefundEvent(Builder builder) {
        id = builder.id;
        type = builder.type;
        price = builder.price;
        count = builder.count;
        payChannel = builder.payChannel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public static final class Builder {
        private String id;
        private int type;
        private String price;
        private int count;
        private String payChannel;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder type(int val) {
            type = val;
            return this;
        }

        public Builder price(String val) {
            price = val;
            return this;
        }

        public Builder count(int val) {
            count = val;
            return this;
        }

        public Builder payChannel(String val) {
            payChannel = val;
            return this;
        }

        public OrderPayRefundEvent build() {
            return new OrderPayRefundEvent(this);
        }
    }

    @Override
    public String toString() {
        return "OrderPayRefundEvent{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", price='" + price + '\'' +
                ", count=" + count +
                ", payChannel='" + payChannel + '\'' +
                '}';
    }
}
