package com.sj.skyblue.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.DisplayUtils;
import com.sj.module_lib.utils.StringUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.adapter.AddServiceRyvAdapter;
import com.sj.skyblue.activity.adapter.itemdecoration.SpaceItemDecoration;
import com.sj.skyblue.constant.PaymentConstants;
import com.sj.skyblue.entity.GoodEntity;
import com.sj.skyblue.entity.ServiceEntity;
import com.sj.skyblue.entity.payment.request.ReqPay;
import com.sj.skyblue.entity.payment.response.RspCancelRevoke;
import com.sj.skyblue.entity.payment.response.RspPay;
import com.sj.skyblue.entity.payment.response.RspQuery;
import com.sj.skyblue.entity.payment.response.RspTerminal;
import com.sj.skyblue.http.PaymentAPI;
import com.sj.skyblue.http.base.BasePayResponse;
import com.sj.skyblue.http.base.PayServerResultBack;
import com.sj.skyblue.manager.SPManager;

import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Sunj on 2018/11/12.
 */

public class SqbPayDialogView {
    private Activity activity;
    private View rootView;
    private AlertDialog dialog;

    private EditText etCode;
    private TextView tvPrice;
    private TextView tvMessage;
    private ImageView imgStatus;
    private Button btSure;
    private Button btCancel;

    private String totalPrice;
    private String transationId;
    private String subject;
    private String code;
    private String operator;
    private ReqPay.GoodDetail goods_details;

    private final int timeCount = 10;
    private int time = 0;

    private final int STATUS_INIT = -1;
    private final int STATUS_PAYING = 0;
    private final int STATUS_PAY_SUCCESS = 1;
    private final int STATUS_PAY_FAIL = 2;
    private final int STATUS_CANCELING = 3;
    private final int STATUS_CANCEL_SUCCESS = 4;
    private final int STATUS_CANCEL_FAIL = 5;
    private final int STATUS_QUERYING = 6;
    private final int STATUS_ERROR = 7;

    private final int PAY = 100;
    private final int PAY_CANCLE = 101;
    private final int PAY_QUERY = 102;

    private int status = STATUS_INIT;

    private PayCallback payCallback;

    Handler hander = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case PAY:
                    doPay();
                    break;
                case PAY_CANCLE:
                    doCancel();
                    break;
                case PAY_QUERY:
                    doQuery();
                    time++;
                    break;
            }
        }
    };

    public void setPayCallback(PayCallback payCallback){
        this.payCallback = payCallback;
    }
    private void doQuery() {
        hander.sendEmptyMessageDelayed(PAY_QUERY, 2000);
        PaymentAPI.query(transationId, new PayServerResultBack<BasePayResponse<RspQuery>, RspQuery>() {
            @Override
            public void onSuccess(RspQuery data) {
                String orderStatus = data.getData().getOrder_status();
                if (PaymentConstants.pay_status_end.containsKey(orderStatus)) {
                    hander.removeMessages(PAY_QUERY);
                    time = 0;
                    if (PaymentConstants.PAID.equals(orderStatus)) {
                        showView(STATUS_PAY_SUCCESS);
                    } else if (PaymentConstants.PAY_CANCELED.equals(orderStatus)) {
                        showView(STATUS_PAY_FAIL, "支付失败");
                    } else {
                        showView(STATUS_PAY_FAIL, "支付状态未知,请联系收钱吧客服");
                    }
                } else {
                    if (time > timeCount) {
                        hander.removeMessages(PAY_QUERY);
                        time = 0;
                        if (PaymentConstants.PAY_ERROR.equals(orderStatus)) {
                            showView(STATUS_PAY_FAIL, "支付失败，不确定是否已经成功充正,请联系收钱吧客服确认是否支付成功");
                        } else {
                            showView(STATUS_PAY_FAIL, "支付状态未知,请联系收钱吧客服");
                        }
                    }
                }
            }

            @Override
            public void onError(String error_code, String error_message) {
                if (time > timeCount) {
                    hander.removeMessages(PAY_QUERY);
                    time = 0;
                    showView(STATUS_PAY_FAIL, "支付状态未知,请联系收钱吧客服");
                }
            }
        });
    }

    /**
     * 支付撤单
     */
    private void doCancel() {
        PaymentAPI.cancel(transationId, new PayServerResultBack<BasePayResponse<RspCancelRevoke>, RspCancelRevoke>() {
            @Override
            public void onSuccess(RspCancelRevoke data) {
                showView(2, "支付失败");
            }

            @Override
            public void onError(String error_code, String error_message) {
                showView(2, "支付失败");
            }
        });
    }

    private void doPay() {
        ReqPay reqPay = new ReqPay.Builder()
                .terminal_sn(SPManager.getInstance().getTerminalSn())
                .client_sn(transationId)
                .dynamic_id(code)
                .operator(operator)
                .subject(subject)
                .total_amount(Float.parseFloat(totalPrice)*100+"")
                .goods_details(goods_details).build();
        PaymentAPI.pay(reqPay, new PayServerResultBack<BasePayResponse<RspPay>, RspPay>() {
            @Override
            public void onSuccess(RspPay data) {
                if (data.getData() != null) {
                    if (!StringUtils.isEmpty(data.getError_code())) {
                        showView(STATUS_PAY_FAIL, data.getError_message());
                    } else {
                        showView(STATUS_QUERYING);
                    }
                } else {
                    showView(STATUS_PAY_FAIL, "支付失败");
                }
            }

            @Override
            public void onError(String error_code, String error_message) {
                showView(STATUS_PAY_FAIL, error_message);
            }
        });
    }

    public SqbPayDialogView(Activity activity, AlertDialog dialog, View view) {
        this.activity = activity;
        this.rootView = view;
        this.dialog = dialog;
    }

    public void initModule(String orderNum, String totalPrice, String subject, String operator, ReqPay.GoodDetail goods_details) {
        if (rootView == null) {
            Logger.e("not root exist");
            return;
        }
        this.totalPrice = totalPrice;
        this.transationId = orderNum;
        this.subject = subject;
        this.operator = operator;
        this.goods_details = goods_details;

        tvPrice= rootView.findViewById(R.id.tv_price);
        etCode = rootView.findViewById(R.id.et_code);
        tvMessage = rootView.findViewById(R.id.tv_message);
        imgStatus = rootView.findViewById(R.id.img_status);
        btSure = rootView.findViewById(R.id.bt_sure);
        btCancel = rootView.findViewById(R.id.bt_cancle);
        etCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    code = etCode.getText().toString();
                    showView(STATUS_PAYING);
                    return true;
                }
                return false;
            }
        });
        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=payCallback){
                    if (status==STATUS_PAY_SUCCESS){
                        payCallback.paySuccess();
                    }else{
                        payCallback.payRetry();
                    }
                }
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dissmiss();
            }
        });
        tvPrice.setText("¥ "+totalPrice);
        showView(STATUS_INIT);

    }

    public void showView(int status) {
        this.showView(status, "");
    }

    public void showView(int status, String msg) {
        this.status = status;
        switch (status) {
            case STATUS_INIT://初始状态
                hander.removeCallbacksAndMessages(null);
                btSure.setEnabled(false);
                btCancel.setEnabled(true);
                etCode.setVisibility(View.VISIBLE);
                etCode.setText(null);
                etCode.requestFocus();
                tvMessage.setText("等待扫描支付码...");
                imgStatus.setVisibility(View.INVISIBLE);
                break;
            case STATUS_PAYING://支付中状态
                btSure.setEnabled(false);
                btCancel.setEnabled(false);
                etCode.setVisibility(View.INVISIBLE);
                etCode.setText(null);
                tvMessage.setText("正在支付，请稍等...");
                imgStatus.setVisibility(View.INVISIBLE);
                hander.sendEmptyMessage(PAY);
                break;
            case STATUS_PAY_FAIL:
                code = null;
                btSure.setEnabled(true);
                btCancel.setEnabled(true);
                etCode.setVisibility(View.INVISIBLE);
                tvMessage.requestFocus();
                tvMessage.setText(msg);
                btSure.setText("重新支付");
                imgStatus.setVisibility(View.VISIBLE);
                imgStatus.setSelected(false);
                break;
            case STATUS_PAY_SUCCESS://支付结束状态
                code = null;
                btSure.setEnabled(true);
                btCancel.setEnabled(true);
                etCode.setVisibility(View.INVISIBLE);
                tvMessage.requestFocus();
                tvMessage.setText(msg);
                btSure.setText("支付成功");
                imgStatus.setVisibility(View.VISIBLE);
                imgStatus.setSelected(true);
                break;
            case STATUS_QUERYING://支付查询状态
                btSure.setEnabled(false);
                btCancel.setEnabled(false);
                etCode.setVisibility(View.INVISIBLE);
                etCode.setText(null);
                tvMessage.setText("正在查询订单状态，请稍等...");
                imgStatus.setVisibility(View.INVISIBLE);
                hander.sendEmptyMessage(PAY_QUERY);
                break;
            case STATUS_CANCELING://支付撤单状态
                btSure.setEnabled(false);
                btCancel.setEnabled(false);
                etCode.setVisibility(View.INVISIBLE);
                etCode.setText(null);
                tvMessage.setText(msg + "，正在自动撤单，请稍等...");
                imgStatus.setVisibility(View.INVISIBLE);
                break;
            case STATUS_CANCEL_SUCCESS://撤单成功
                btSure.setEnabled(false);
                btCancel.setEnabled(false);
                etCode.setVisibility(View.INVISIBLE);
                etCode.setText(null);
                tvMessage.setText(msg + "，正在自动撤单，请稍等...");
                imgStatus.setVisibility(View.INVISIBLE);
                break;
            case STATUS_CANCEL_FAIL://撤单失败
                btSure.setEnabled(false);
                btCancel.setEnabled(false);
                etCode.setVisibility(View.INVISIBLE);
                etCode.setText(null);
                tvMessage.setText(msg + "，正在自动撤单，请稍等...");
                imgStatus.setVisibility(View.INVISIBLE);
                break;
            case STATUS_ERROR://异常
                btSure.setEnabled(true);
                btCancel.setEnabled(true);
                etCode.setVisibility(View.INVISIBLE);
                etCode.setText(null);
                tvMessage.setText(msg + "，请联系收钱吧客服！");
                imgStatus.setVisibility(View.INVISIBLE);
                imgStatus.setSelected(false);
                break;
        }
    }

    private void dissmiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
    public interface PayCallback{
        void paySuccess();
        void payRetry();
    }
}
