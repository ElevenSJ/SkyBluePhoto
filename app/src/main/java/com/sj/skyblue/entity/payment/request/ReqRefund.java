package com.sj.skyblue.entity.payment.request;

/**
 * Created by Sunj on 2018/11/21.
 */

public class ReqRefund {

    //    收钱吧终端ID
    final String terminal_sn;
    //    商户系统订单号
    final String client_sn;
    //    商户退款流水号
    final String client_tsn;
    //    退款序列号
    final String refund_request_no;
    //    退款金额(以分为单位,不超过10位纯数)
    final String refund_amount;
    //    门店操作员
    final String operator;
    //    商品详情
    final ReqPay.GoodDetail goods_details;

    private ReqRefund(Builder builder) {
        terminal_sn = builder.terminal_sn;
        client_sn = builder.client_sn;
        client_tsn = builder.client_tsn;
        refund_request_no = builder.refund_request_no;
        refund_amount = builder.refund_amount;
        operator = builder.operator;
        goods_details = builder.goods_details;
    }


    public static final class Builder {
        private String terminal_sn;
        private String client_sn;
        private String client_tsn;
        private String refund_request_no;
        private String refund_amount;
        private String operator;
        private ReqPay.GoodDetail goods_details;

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

        public Builder client_tsn(String val) {
            client_tsn = val;
            return this;
        }

        public Builder refund_request_no(String val) {
            refund_request_no = val;
            return this;
        }

        public Builder refund_amount(String val) {
            refund_amount = val;
            return this;
        }

        public Builder operator(String val) {
            operator = val;
            return this;
        }

        public Builder goods_details(ReqPay.GoodDetail val) {
            goods_details = val;
            return this;
        }

        public ReqRefund build() {
            return new ReqRefund(this);
        }
    }
}
