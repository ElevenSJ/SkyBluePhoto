package com.sj.skyblue.entity.post;

/**
 * Created by Sunj on 2018/11/28.
 */

public class BindDevice {

    /**
     * deviceId : 123456123
     * terminalCode : 123456
     */

    private String deviceId;
    private String terminalCode;

    private BindDevice(Builder builder) {
        deviceId = builder.deviceId;
        terminalCode = builder.terminalCode;
    }


    public static final class Builder {
        private String deviceId;
        private String terminalCode;

        public Builder() {
        }

        public Builder deviceId(String val) {
            deviceId = val;
            return this;
        }

        public Builder terminalCode(String val) {
            terminalCode = val;
            return this;
        }

        public BindDevice build() {
            return new BindDevice(this);
        }
    }
}
