package com.sj.skyblue.entity.payment.response;

/**
 * Created by Sunj on 2018/11/21.
 */

public class RspRefund {

    /**
     * result_code : REFUND_SUCCESS
     * data : {"sn":"7894259244067218","client_sn":"12345677767776","status":"SUCCESS","payway":"3","sub_payway":"1","order_status":"REFUNDED","trade_no":"2006101016201512080095793262","total_amount":"1","net_amount":"0","finish_time":"1449563206776","channel_finish_time":"1449563206632"}
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
         * sn : 7894259244067218
         * client_sn : 12345677767776
         * status : SUCCESS
         * payway : 3
         * sub_payway : 1
         * order_status : REFUNDED
         * trade_no : 2006101016201512080095793262
         * total_amount : 1
         * net_amount : 0
         * finish_time : 1449563206776
         * channel_finish_time : 1449563206632
         */

        private String sn;
        private String client_sn;
        private String status;
        private String payway;
        private String sub_payway;
        private String order_status;
        private String trade_no;
        private String total_amount;
        private String net_amount;
        private String finish_time;
        private String channel_finish_time;

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
    }
}
