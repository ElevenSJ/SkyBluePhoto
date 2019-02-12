package com.sj.skyblue.entity.payment.request;

/**
 * Created by Sunj on 2018/11/21.
 */

public class ReqCancel {

    final String terminal_sn;
    final String client_sn;

    private ReqCancel(Builder builder) {
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

        public ReqCancel build() {
            return new ReqCancel(this);
        }
    }
}
