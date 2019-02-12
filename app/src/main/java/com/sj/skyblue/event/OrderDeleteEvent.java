package com.sj.skyblue.event;

/**
 * Created by Sunj on 2018/11/18.
 */

public class OrderDeleteEvent {

    public String id;
    public int type;
    public int positon;

    private OrderDeleteEvent(Builder builder) {
        id = builder.id;
        type = builder.type;
        positon = builder.positon;
    }


    public static final class Builder {
        private String id;
        private int type;
        private int positon;
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
        public Builder positon(int val) {
            positon = val;
            return this;
        }


        public OrderDeleteEvent build() {
            return new OrderDeleteEvent(this);
        }
    }
}
