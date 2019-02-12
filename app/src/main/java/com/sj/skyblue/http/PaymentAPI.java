package com.sj.skyblue.http;

import com.alibaba.fastjson.JSON;
import com.jady.retrofitclient.HttpManager;
import com.jady.retrofitclient.callback.HttpCallback;
import com.orhanobut.logger.Logger;
import com.sj.skyblue.constant.PaymentConstants;
import com.sj.skyblue.entity.payment.request.ReqActivate;
import com.sj.skyblue.entity.payment.request.ReqCancel;
import com.sj.skyblue.entity.payment.request.ReqCheckin;
import com.sj.skyblue.entity.payment.request.ReqPay;
import com.sj.skyblue.entity.payment.request.ReqQuery;
import com.sj.skyblue.entity.payment.request.ReqRefund;
import com.sj.skyblue.manager.SPManager;
import com.sj.skyblue.utils.MD5Util;
import com.sj.skyblue.utils.SystemUtil;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sunj on 2018/11/8.
 */

public class PaymentAPI {

    /**
     * 激活
     *
     * @param code
     * @param callback
     */
    public static void activate(String code, HttpCallback callback) {
        try {
            ReqActivate reqActivate = new ReqActivate.Builder()
                    .app_id(PaymentConstants.APPID)
                    .code(code)
                    .device_id(SystemUtil.getDeviceSN())
                    .build();
            HttpManager.setTmpBaseUrl(PaymentConstants.API_DOMAIN);
            Map<String, String> tmpHeader = new HashMap<>(1);
            tmpHeader.put("Authorization", PaymentConstants.VENDOR_SN + " " + MD5Util.encryptMd5(JSON.toJSONString(reqActivate) + PaymentConstants.VENDOR_KEY));
            HttpManager.addTmpHeaders(tmpHeader);
            HttpManager.postByBody(PaymentConstants.ACTIVATE, reqActivate, callback);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Logger.e("PaymentAPI activate UnsupportedEncodingException");
            if (callback != null) {
                callback.onFailed("-100", "签名异常");
            }
        }
    }

    /**
     * 签到
     *
     * @param callback
     */
    public static void checkin(HttpCallback callback) {
        try {
            String terminal_sn = SPManager.getInstance().getTerminalSn();
            String terminal_key = SPManager.getInstance().getTerminalKey();
            ReqCheckin reqCheckin = new ReqCheckin.Builder(SystemUtil.getDeviceSN(), terminal_sn).build();
            HttpManager.setTmpBaseUrl(PaymentConstants.API_DOMAIN);
            Map<String, String> tmpHeader = new HashMap<>(1);
            tmpHeader.put("Authorization", terminal_sn + " " + MD5Util.encryptMd5(JSON.toJSONString(reqCheckin) + terminal_key));
            HttpManager.addTmpHeaders(tmpHeader);
            HttpManager.postByBody(PaymentConstants.CHECKIN, reqCheckin, callback);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Logger.e("PaymentAPI checkin UnsupportedEncodingException");
            if (callback != null) {
                callback.onFailed("-100", "签名异常");
            }
        }
    }

    /**
     * 支付
     *
     * @param callback
     */
    public static void pay(ReqPay reqPay, HttpCallback callback) {
        try {
            String terminal_sn = SPManager.getInstance().getTerminalSn();
            String terminal_key = SPManager.getInstance().getTerminalKey();

            HttpManager.setTmpBaseUrl(PaymentConstants.API_DOMAIN);
            Map<String, String> tmpHeader = new HashMap<>(1);
            tmpHeader.put("Authorization", terminal_sn + " " + MD5Util.encryptMd5(JSON.toJSONString(reqPay) + terminal_key));
            HttpManager.addTmpHeaders(tmpHeader);
            HttpManager.postByBody(PaymentConstants.PAY, reqPay, callback);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Logger.e("PaymentAPI pay UnsupportedEncodingException");
            if (callback != null) {
                callback.onFailed("-100", "签名异常");
            }
        }
    }

    /**
     * 退款
     *
     * @param callback
     */
    public static void refund(String orderNum, String client_tsn, String refund_request_no, String refund_amount, ReqPay.GoodDetail goods_details, HttpCallback callback) {
        try {
            String terminal_sn = SPManager.getInstance().getTerminalSn();
            String terminal_key = SPManager.getInstance().getTerminalKey();
            String operator = SPManager.getInstance().getOperatorName();
            ReqRefund reqRefund = new ReqRefund.Builder()
                    .terminal_sn(terminal_sn)
                    .client_sn(orderNum)
                    .client_tsn(client_tsn)
                    .operator(operator)
                    .refund_amount(refund_amount)
                    .refund_request_no(refund_request_no)
                    .goods_details(goods_details).build();
            HttpManager.setTmpBaseUrl(PaymentConstants.API_DOMAIN);
            Map<String, String> tmpHeader = new HashMap<>(1);
            tmpHeader.put("Authorization", terminal_sn + " " + MD5Util.encryptMd5(JSON.toJSONString(reqRefund) + terminal_key));
            HttpManager.addTmpHeaders(tmpHeader);
            HttpManager.postByBody(PaymentConstants.REFUND, reqRefund, callback);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Logger.e("PaymentAPI pay UnsupportedEncodingException");
            if (callback != null) {
                callback.onFailed("-100", "签名异常");
            }
        }
    }

    /**
     *  自动撤单，支付失败情况下
     *
     * @param callback
     */
    public static void cancel(String orderNum, HttpCallback callback) {
        try {
            String terminal_sn = SPManager.getInstance().getTerminalSn();
            String terminal_key = SPManager.getInstance().getTerminalKey();
            ReqCancel reqCancel = new ReqCancel.Builder()
                    .terminal_sn(terminal_sn)
                    .client_sn(orderNum).build();
            HttpManager.setTmpBaseUrl(PaymentConstants.API_DOMAIN);
            Map<String, String> tmpHeader = new HashMap<>(1);
            tmpHeader.put("Authorization", terminal_sn + " " + MD5Util.encryptMd5(JSON.toJSONString(reqCancel) + terminal_key));
            HttpManager.addTmpHeaders(tmpHeader);
            HttpManager.postByBody(PaymentConstants.CANCEL, reqCancel, callback);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Logger.e("PaymentAPI pay UnsupportedEncodingException");
            if (callback != null) {
                callback.onFailed("-100", "签名异常");
            }
        }
    }
    /**
     * 手动撤单，支付成功情况下
     *
     * @param callback
     */
    public static void revoke(String orderNum, HttpCallback callback) {
        try {
            String terminal_sn = SPManager.getInstance().getTerminalSn();
            String terminal_key = SPManager.getInstance().getTerminalKey();
            ReqCancel reqCancel = new ReqCancel.Builder()
                    .terminal_sn(terminal_sn)
                    .client_sn(orderNum).build();
            HttpManager.setTmpBaseUrl(PaymentConstants.API_DOMAIN);
            Map<String, String> tmpHeader = new HashMap<>(1);
            tmpHeader.put("Authorization", terminal_sn + " " + MD5Util.encryptMd5(JSON.toJSONString(reqCancel) + terminal_key));
            HttpManager.addTmpHeaders(tmpHeader);
            HttpManager.postByBody(PaymentConstants.REVOKE, reqCancel, callback);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Logger.e("PaymentAPI pay UnsupportedEncodingException");
            if (callback != null) {
                callback.onFailed("-100", "签名异常");
            }
        }
    }
    /**
     * 查询订单状态
     *
     * @param callback
     */
    public static void query(String orderNum, HttpCallback callback) {
        try {
            String terminal_sn = SPManager.getInstance().getTerminalSn();
            String terminal_key = SPManager.getInstance().getTerminalKey();
            ReqQuery reqQuery = new ReqQuery.Builder()
                    .terminal_sn(terminal_sn)
                    .client_sn(orderNum).build();
            HttpManager.setTmpBaseUrl(PaymentConstants.API_DOMAIN);
            Map<String, String> tmpHeader = new HashMap<>(1);
            tmpHeader.put("Authorization", terminal_sn + " " + MD5Util.encryptMd5(JSON.toJSONString(reqQuery) + terminal_key));
            HttpManager.addTmpHeaders(tmpHeader);
            HttpManager.postByBody(PaymentConstants.QUERY, reqQuery, callback);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Logger.e("PaymentAPI pay UnsupportedEncodingException");
            if (callback != null) {
                callback.onFailed("-100", "签名异常");
            }
        }
    }
}
