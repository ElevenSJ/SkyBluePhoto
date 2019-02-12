package com.sj.skyblue.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sunj on 2018/11/8.
 */

public abstract class PaymentConstants {

    public final static String activateFileExt = ".activate";
    public final static String checkinFileExt = ".checkin";

    public final static String API_DOMAIN = "https://api-vendor.shouqianba.com";

    public final static String API_DOMAIN_SANDBOX = "https://api-sandbox.test.shouqianba.com";

    public final static String VENDOR_SN = "91800524";
    public final static String VENDOR_KEY = "06408fd668357f0beab587b35180409e";
    public final static String CODE = "57469275";
    public final static String APPID = "2018110800001093";

    /**
     * 激活
     * 每个设备只需激活一次
     */
    public final static String ACTIVATE = "/terminal/activate";
    /**
     * 签到
     * 需要每天签到一次
     */
    public final static String CHECKIN = "/terminal/checkin";
    /**
     * 付款
     * 支付完成后立马开始轮询查询支付结果
     */
    public final static String PAY = "/upay/v2/pay";
    /**
     * 退款
     */
    public final static String REFUND = "/upay/v2/refund";
    /**
     * 支付未返回成功自动撤单
     * 支付失败需要调用撤单
     */
    public final static String CANCEL = "/upay/v2/cancel";
    /**
     * 支付成功手动撤单（当天有效）
     */
    public final static String REVOKE = "/upay/v2/revoke";
    /**
     * 查询
     * 支付结果以查询结果为准
     */
    public final static String QUERY = "/upay/v2/query";


    //      收钱吧
    //      1:支付宝
    //      3:微信
    //      4:百度钱包
    //      5:京东钱包
    //      6:qq钱包
    public final  static  Map<String, String> payChannelName = new HashMap<>();
    static {
        payChannelName.put("1","支付宝");
        payChannelName.put("3","微信");
        payChannelName.put("4","百度钱包");
        payChannelName.put("5","京东钱包");
        payChannelName.put("6","qq钱包");

    }

    //支付状态
    public final  static  Map<String, Boolean> pay_status_end = new HashMap<>();
    static {
        pay_status_end.put("PAID",true);
        pay_status_end.put("PAY_CANCELED",true);
        pay_status_end.put("REFUNDED",true);
        pay_status_end.put("PARTIAL_REFUNDED",true);
        pay_status_end.put("CANCELED",true);

    }

    //业务状态 result_code
    //本次业务执行成功
    public final static String SUCCESS = "SUCCESS";
    //本次业务执行失败
    public final static String FAIL = "FAIL";
    //本次业务进行中
    public final static String INPROGRESS = "INPROGRESS";
    //本次业务执行结果未知
    public final static String ERROR = "ERROR";

    //业务状态 result_code
    public final static String PAY_SUCCESS = "PAY_SUCCESS";
    public final static String PAY_FAIL = "PAY_FAIL";
    public final static String PAY_IN_PROGRESS = "PAY_IN_PROGRESS";
    public final static String PAY_FAIL_ERROR = "PAY_FAIL_ERROR";
    public final static String PAY_FAIL_IN_PROGRESS = "PAY_FAIL_IN_PROGRESS";
    public final static String CANCEL_SUCCESS = "CANCEL_SUCCESS";
    public final static String CANCEL_ABORT_ERROR = "CANCEL_ABORT_ERROR";
    public final static String CANCEL_ABORT_SUCCESS = "CANCEL_ABORT_SUCCESS";
    public final static String CANCEL_IN_PROGRESS = "CANCEL_IN_PROGRESS";
    public final static String CANCEL_ABORT_IN_PROGRESS = "CANCEL_ABORT_IN_PROGRESS";
    public final static String REFUND_SUCCESS = "REFUND_SUCCESS";
    public final static String REFUND_FAIL = "REFUND_FAIL";
    public final static String REFUND_IN_PROGRESS = "REFUND_IN_PROGRESS";

    //订单状态 order_status
    //订单已创建/支付中
    public final static String CREATED = "CREATED";
    //订单支付成功
    public final static String PAID = "PAID";
    //支付失败并且已经成功充正
    public final static String PAY_CANCELED = "PAY_CANCELED";
    //支付失败，不确定是否已经成功充正,请联系收钱吧客服确认是否支付成功
    public final static String PAY_ERROR = "PAY_ERROR";
    //	已成功全额退款
    public final static String REFUNDED = "REFUNDED";
    //已成功部分退款
    public final static String PARTIAL_REFUNDED = "PARTIAL_REFUNDED";
    //退款失败并且不确定第三方支付通道的最终退款状态
    public final static String REFUND_ERROR = "REFUND_ERROR";
    //客户端发起的撤单已成功
    public final static String CANCELED = "CANCELED";
    //客户端发起的撤单失败并且不确定第三方支付通道的最终状态
    public final static String CANCEL_ERROR = "CANCEL_ERROR";
    //撤单进行中
    public final static String CANCEL_INPROGRESS = "CANCEL_INPROGRESS";
    //无效的状态码
    public final static String INVALID_STATUS_CODE = "INVALID_STATUS_CODE";

}
