package com.sj.skyblue.manager;

import android.os.Looper;

import com.sj.skyblue.entity.payment.request.ReqPay;
import com.sj.skyblue.entity.payment.response.RspTerminal;
import com.sj.skyblue.http.PaymentAPI;
import com.sj.skyblue.http.base.BasePayResponse;
import com.sj.skyblue.http.base.PayServerResultBack;
import com.sj.skyblue.manager.handler.PayMentHandler;

/**
 * Created by Sunj on 2018/11/8.
 * 收钱吧支付管理
 */

public class PaymentManager {

    private PayMentHandler handler;
    private static final PaymentManager ourInstance = new PaymentManager();

    public static PaymentManager getInstance() {
        return ourInstance;
    }

    private PaymentManager() {
        handler = new PayMentHandler(Looper.getMainLooper());
    }

    public void doPay(String orderNum, String total_amount, String code, String subject, ReqPay.GoodDetail goods_details) {
    }

    public void doCheckin() {
        PaymentAPI.checkin(new PayServerResultBack<BasePayResponse<RspTerminal>, RspTerminal>() {
            @Override
            public void onSuccess(RspTerminal data) {
//                Message msg = handler.obtainMessage();
//                msg.what = handler.SUCCESS_CODE;
//                msg.obj = data;
//                msg.sendToTarget();
            }

            @Override
            public void onError(String error_code, String error_message) {
//                Message msg = handler.obtainMessage();
//                msg.what = handler.FAIL_CODE;
//                msg.obj = new FailMessage.Builder().code(error_code).message(error_message).build();
//                msg.sendToTarget();
            }
        });
    }

    public interface PaymentListener<T> {
        void success(T data);

        void fail(String code, String msg);
    }
}
