package com.sj.skyblue.entity.payment.response;

import java.util.List;

/**
 * Created by Sunj on 2018/11/23.
 */

public class RspQuery {


    /**
     * result_code : SUCCESS
     * data : {"client_tsn":"726d47ded14818c906cd1a006d4e5050","store_id":"test","subject":"test","payway":"2","description":"[{\"id\":\"\",\"name\":\"未命名商品\",\"num\":\"1\",\"price\":\"3000\"}]\n","payment_list":[{"type":"BANKCARD_DEBIT","amount_total":"1"},{"type":"DISCOUNT_CHANNEL_MCH","amount_total":"100"}],"client_sn":"test","operator":"test","sub_payway":"1","order_status":"PAID","total_amount":"300000","ctime":"1498446309605","sn":"7895259867120667","net_amount":"0","terminal_id":"a2369b8b-546f-42e1-baec-e1d54690bab8","status":"SUCCESS"}
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
         * client_tsn : 726d47ded14818c906cd1a006d4e5050
         * store_id : test
         * subject : test
         * payway : 2
         * description : [{"id":"","name":"未命名商品","num":"1","price":"3000"}]

         * payment_list : [{"type":"BANKCARD_DEBIT","amount_total":"1"},{"type":"DISCOUNT_CHANNEL_MCH","amount_total":"100"}]
         * client_sn : test
         * operator : test
         * sub_payway : 1
         * order_status : PAID
         * total_amount : 300000
         * ctime : 1498446309605
         * sn : 7895259867120667
         * net_amount : 0
         * terminal_id : a2369b8b-546f-42e1-baec-e1d54690bab8
         * status : SUCCESS
         */

        private String client_tsn;
        private String store_id;
        private String subject;
        private String payway;
        private String description;
        private String client_sn;
        private String operator;
        private String sub_payway;
        private String order_status;
        private String total_amount;
        private String ctime;
        private String sn;
        private String net_amount;
        private String terminal_id;
        private String status;
        private List<PaymentListBean> payment_list;

        public String getClient_tsn() {
            return client_tsn;
        }

        public void setClient_tsn(String client_tsn) {
            this.client_tsn = client_tsn;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getPayway() {
            return payway;
        }

        public void setPayway(String payway) {
            this.payway = payway;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getClient_sn() {
            return client_sn;
        }

        public void setClient_sn(String client_sn) {
            this.client_sn = client_sn;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
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

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getNet_amount() {
            return net_amount;
        }

        public void setNet_amount(String net_amount) {
            this.net_amount = net_amount;
        }

        public String getTerminal_id() {
            return terminal_id;
        }

        public void setTerminal_id(String terminal_id) {
            this.terminal_id = terminal_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
