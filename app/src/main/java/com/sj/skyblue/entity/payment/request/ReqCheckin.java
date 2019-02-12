package com.sj.skyblue.entity.payment.request;

/**
 * Created by Sunj on 2018/11/8.
 */

public class ReqCheckin {

    final String device_id;
    final String terminal_sn;

    private ReqCheckin(Builder builder) {
        device_id = builder.device_id;
        terminal_sn = builder.terminal_sn;
    }

    public static final class Builder {
        private String device_id;
        private String terminal_sn;

        public Builder(String device_id,String terminal_sn) {
            this.device_id =device_id;
            this.terminal_sn =terminal_sn;
        }

        public ReqCheckin build() {
            return new ReqCheckin(this);
        }
    }
}
