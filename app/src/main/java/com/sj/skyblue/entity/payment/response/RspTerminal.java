package com.sj.skyblue.entity.payment.response;

/**
 * Created by Sunj on 2018/11/8.
 */

public class RspTerminal {
    String terminal_sn;
    String terminal_key;

    public String getTerminal_sn() {
        return terminal_sn;
    }

    public void setTerminal_sn(String terminal_sn) {
        this.terminal_sn = terminal_sn;
    }

    public String getTerminal_key() {
        return terminal_key;
    }

    public void setTerminal_key(String terminal_key) {
        this.terminal_key = terminal_key;
    }

    @Override
    public String toString() {
        return "RspTerminal{" +
                "terminal_sn='" + terminal_sn + '\'' +
                ", terminal_key='" + terminal_key + '\'' +
                '}';
    }
}
