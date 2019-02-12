package com.sj.skyblue.entity.post;

/**
 * Created by Sunj on 2018/12/4.
 */

public class PayRefundBean {

    /**
     * id : OSPundefinediNeV
     * type : 5
     * token : 92233704931417131378d8c9a6be3494075b98a41f713bcc767
     * count : 1
     */

    private String id;
    private int type;
    private String token;
    private int count;

    private PayRefundBean(Builder builder) {
        id = builder.id;
        type = builder.type;
        token = builder.token;
        count = builder.count;
    }

    public static final class Builder {
        private String id;
        private int type;
        private String token;
        private int count;

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

        public Builder token(String val) {
            token = val;
            return this;
        }

        public Builder count(int val) {
            count = val;
            return this;
        }

        public PayRefundBean build() {
            return new PayRefundBean(this);
        }
    }
}
