package com.sj.skyblue.entity.payment.request;

/**
 * Created by Sunj on 2018/11/21.
 */

public class ReqQuery {

    final String terminal_sn;
    final String client_sn;

    private ReqQuery(Builder builder) {
        terminal_sn = builder.terminal_sn;
        client_sn = builder.client_sn;
    }


    public static final class Builder {
        private String terminal_sn;
        private String client_sn;

        public Builder() {
        }

        public Builder terminal_sn(String val) {
            terminal_sn = val;
            return this;
        }

        public Builder client_sn(String val) {
            client_sn = val;
            return this;
        }

        public ReqQuery build() {
            return new ReqQuery(this);
        }
    }
}
