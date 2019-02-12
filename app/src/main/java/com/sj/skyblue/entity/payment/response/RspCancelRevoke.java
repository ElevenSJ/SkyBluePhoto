package com.sj.skyblue.entity.payment.response;

/**
 * Created by Sunj on 2018/11/21.
 */

public class RspCancelRevoke {

    /**
     * result_code : CANCEL_SUCCESS
     * data : {"sn":"7894259244064831","client_sn":"22345677767776","status":"SUCCESS","payway":"3","sub_payway":"1","order_status":"CANCELED","total_amount":"1","net_amount":"0","finish_time":"1450090828489","subject":"wx","store_id":"49"}
     */

    private String result_code;
    private String error_code;
    private String error_message;
    private DataBean data;

    public String getResult_code() {
        return result_code;
    }

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
         * sn : 7894259244064831
         * client_sn : 22345677767776
         * status : SUCCESS
         * payway : 3
         * sub_payway : 1
         * order_status : CANCELED
         * total_amount : 1
         * net_amount : 0
         * finish_time : 1450090828489
         * subject : wx
         * store_id : 49
         */

        private String sn;
        private String client_sn;
        private String status;
        private String payway;
        private String sub_payway;
        private String order_status;
        private String total_amount;
        private String net_amount;
        private String finish_time;
        private String subject;
        private String store_id;

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
    }
}
