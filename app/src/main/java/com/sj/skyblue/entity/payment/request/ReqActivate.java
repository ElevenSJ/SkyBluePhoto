package com.sj.skyblue.entity.payment.request;

/**
 * Created by Sunj on 2018/11/8.
 */

public class ReqActivate {

    final String app_id;
    final String code;
    final String device_id;
    final String device_name;

    private ReqActivate(Builder builder) {
        app_id = builder.app_id;
        code = builder.code;
        device_id = builder.device_id;
        device_name = builder.device_name;
    }


    public static final class Builder {
        private String app_id;
        private String code;
        private String device_id;
        private String device_name;

        public Builder() {
        }

        public Builder app_id(String val) {
            app_id = val;
            return this;
        }

        public Builder code(String val) {
            code = val;
            return this;
        }

        public Builder device_id(String val) {
            device_id = val;
            return this;
        }

        public Builder device_name(String val) {
            device_name = val;
            return this;
        }

        public ReqActivate build() {
            return new ReqActivate(this);
        }
    }
}
