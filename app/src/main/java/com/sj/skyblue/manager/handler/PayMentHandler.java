package com.sj.skyblue.manager.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by Sunj on 2018/11/8.
 */

public class PayMentHandler extends Handler {

    public final int PAY_FAIL_CODE = 1001;
    public final int PAY_SUCCESS_CODE = 1002;
    public final int PAY_INPROGRESS_CODE = 1003;
    public final int PAY_CANCLE_FAIL_CODE = 1004;
    public final int PAY_CANCLE_SUCCESS_CODE = 1005;
    public final int PAY_QUERY_FAIL_CODE = 1006;
    public final int PAY_QUERY_SUCCESS_CODE = 1007;

    public PayMentHandler(Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case PAY_FAIL_CODE:
                break;
            case PAY_SUCCESS_CODE:
                break;
        }
    }

}
