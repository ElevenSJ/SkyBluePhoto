package com.sj.skyblue.entity.payment.request;

import com.sj.skyblue.manager.SPManager;

/**
 * Created by Sunj on 2018/11/20.
 */

public class ReqPay {
    //    收钱吧终端ID
    final String terminal_sn;
    //    商户系统订单号
    final String client_sn;
    //    交易总金额(以分为单位,不超过10位纯数)
    final String total_amount;
    //    条码内容
    final String dynamic_id;
    //    交易简介(证件照/全家福)
    final String subject;
    //    门店操作员
    final String operator;
    //    商品详情
    final GoodDetail goods_details;

    public String getTerminal_sn() {
        return terminal_sn;
    }

    public String getClient_sn() {
        return client_sn;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public String getDynamic_id() {
        return dynamic_id;
    }

    public String getSubject() {
        return subject;
    }

    public String getOperator() {
        return operator;
    }

    public GoodDetail getGoods_details() {
        return goods_details;
    }

    private ReqPay(Builder builder) {
        terminal_sn = builder.terminal_sn;
        client_sn = builder.client_sn;
        total_amount = builder.total_amount;
        dynamic_id = builder.dynamic_id;
        subject = builder.subject;
        operator = builder.operator;
        goods_details = builder.goods_details;


    }

    public static class GoodDetail {
        //        商品的编号
        final String goods_id;
        //商品名称
        final String goods_name;
        //        商品数量
        final String quantity;
        //        商品单价
        final String price;
        //        优惠类型，0表示没有优惠，1表示支付机构优惠，为1会把相关信息送到支付机构
        final String promotion_type;

        private GoodDetail(Builder builder) {
            goods_id = builder.goods_id;
            goods_name = builder.goods_name;
            quantity = builder.quantity;
            price = builder.price;
            promotion_type = builder.promotion_type;
        }


        public static final class Builder {
            private String goods_id;
            private String goods_name;
            private String quantity;
            private String price;
            private String promotion_type;

            public Builder() {
            }

            public Builder goods_id(String val) {
                goods_id = val;
                return this;
            }

            public Builder goods_name(String val) {
                goods_name = val;
                return this;
            }

            public Builder quantity(String val) {
                quantity = val;
                return this;
            }

            public Builder price(String val) {
                price = val;
                return this;
            }

            public Builder promotion_type(String val) {
                promotion_type = val;
                return this;
            }

            public GoodDetail build() {
                return new GoodDetail(this);
            }
        }
    }

    public static final class Builder {
        private String terminal_sn = SPManager.getInstance().getTerminalSn();
        private String client_sn;
        private String total_amount;
        private String dynamic_id;
        private String subject;
        private String operator;
        private GoodDetail goods_details;

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

        public Builder total_amount(String val) {
            total_amount = val;
            return this;
        }

        public Builder dynamic_id(String val) {
            dynamic_id = val;
            return this;
        }

        public Builder subject(String val) {
            subject = val;
            return this;
        }

        public Builder operator(String val) {
            operator = val;
            return this;
        }

        public Builder goods_details(GoodDetail val) {
            goods_details = val;
            return this;
        }

        public ReqPay build() {
            return new ReqPay(this);
        }
    }
}
