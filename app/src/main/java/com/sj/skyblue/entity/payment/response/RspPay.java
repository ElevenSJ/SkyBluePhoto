package com.sj.skyblue.entity.payment.response;

import com.sj.skyblue.constant.PaymentConstants;

import java.util.List;

/**
 * Created by Sunj on 2018/11/21.
 */

public class RspPay {

    /**
     * result_code : PAY_SUCCESS
     * data : {"sn":"7893259247405832","client_sn":"w145206259311176","client_tsn":"w145206259311176","ctime":"1492506701411","status":"SUCCESS","payway":"3","sub_payway":"1","order_status":"PAID","payer_login":"oyBevtwYm70JVPTFyGlvmKW3IO9U","payer_uid":"oSTAxt7Rkjr7Jtk0vtf-cFWiHjcs","trade_no":"4003262001201704187463804544","total_amount":"101","net_amount":"101","finish_time":"1492506702864","channel_finish_time":"1492506702000","subject":"Apple iPhone 6s plus (A1699) 16G 玫瑰金色 移动联通电信4G手机","store_id":"100ed809-af9a-11e5-9ec3-00163e00625b","operator":"test","payment_list":[{"type":"BANKCARD_DEBIT","amount_total":"1"},{"type":"DISCOUNT_CHANNEL_MCH","amount_total":"100"}]}
     */

    private String result_code;
    private String error_code;
    private String error_message;
    private DataBean data;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * sn : 7893259247405832
         * client_sn : w145206259311176
         * client_tsn : w145206259311176
         * ctime : 1492506701411
         * status : SUCCESS
         * payway : 3
         * sub_payway : 1
         * order_status : PAID
         * payer_login : oyBevtwYm70JVPTFyGlvmKW3IO9U
         * payer_uid : oSTAxt7Rkjr7Jtk0vtf-cFWiHjcs
         * trade_no : 4003262001201704187463804544
         * total_amount : 101
         * net_amount : 101
         * finish_time : 1492506702864
         * channel_finish_time : 1492506702000
         * subject : Apple iPhone 6s plus (A1699) 16G 玫瑰金色 移动联通电信4G手机
         * store_id : 100ed809-af9a-11e5-9ec3-00163e00625b
         * operator : test
         * payment_list : [{"type":"BANKCARD_DEBIT","amount_total":"1"},{"type":"DISCOUNT_CHANNEL_MCH","amount_total":"100"}]
         */

        private String sn;
        private String client_sn;
        private String client_tsn;
        private String ctime;
        private String status;
        private String payway;
        private String sub_payway;
        private String order_status;
        private String payer_login;
        private String payer_uid;
        private String trade_no;
        private String total_amount;
        private String net_amount;
        private String finish_time;
        private String channel_finish_time;
        private String subject;
        private String store_id;
        private String operator;
        private List<PaymentListBean> payment_list;

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getClient_sn() {
            return client_sn;
        }

        public void setClient_sn(String client_sn) {
            this.client_sn = client_sn;
        }

        public String getClient_tsn() {
            return client_tsn;
        }

        public void setClient_tsn(String client_tsn) {
            this.client_tsn = client_tsn;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayway() {
            return payway;
        }

        public void setPayway(String payway) {
            this.payway = payway;
        }

        public String getSub_payway() {
            return sub_payway;
        }

        public void setSub_payway(String sub_payway) {
            this.sub_payway = sub_payway;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getPayer_login() {
            return payer_login;
        }

        public void setPayer_login(String payer_login) {
            this.payer_login = payer_login;
        }

        public String getPayer_uid() {
            return payer_uid;
        }

        public void setPayer_uid(String payer_uid) {
            this.payer_uid = payer_uid;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getNet_amount() {
            return net_amount;
        }

        public void setNet_amount(String net_amount) {
            this.net_amount = net_amount;
        }

        public String getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(String finish_time) {
            this.finish_time = finish_time;
        }

        public String getChannel_finish_time() {
            return channel_finish_time;
        }

        public void setChannel_finish_time(String channel_finish_time) {
            this.channel_finish_time = channel_finish_time;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public List<PaymentListBean> getPayment_list() {
            return payment_list;
        }

        public void setPayment_list(List<PaymentListBean> payment_list) {
            this.payment_list = payment_list;
        }

        public static class PaymentListBean {
            /**
             * type : BANKCARD_DEBIT
             * amount_total : 1
             */

            private String type;
            private String amount_total;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAmount_total() {
                return amount_total;
            }

            public void setAmount_total(String amount_total) {
                this.amount_total = amount_total;
            }
        }
    }
}
