package com.sj.module_lib.http;

import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.jady.retrofitclient.callback.HttpCallback;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.StringUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.module_lib.utils.Utils;

import java.lang.reflect.Type;

/**
 * Created by Sunj on 2018/7/7.
 */
public abstract class ServerResultBack extends HttpCallback<String> {

    @Override
    public void onResolve(String t) {
        BaseResponse callbackData = JSON.parseObject(t, BaseResponse.class);
        if (callbackData.getCode().equals("1")) {
            onFinish();
            try {
                BaseResponse result = JSON.parseObject(t, getType());
                this.onSuccess(result.getData());
            } catch (JSONException exception) {
                exception.printStackTrace();
                onFailed("-100", "未查询到数据");
            }
        } else {
            onFailed(callbackData.getCode(), callbackData.getMessage());
        }
    }

    @Override
    public void onFailed(String error_code, String error_message) {
        Logger.e("error_code:" + error_code + "  error_message:" + error_message);
        onFinish();
        if (enableShowToast()) {
            ToastUtils.showShortToast(error_message);
        }
        //其他设备登录统一处理
        if (StringUtils.isEmpty(error_code) || error_code.equals("9") || error_code.equals("4")) {
            if (Utils.getMainActivity() != null) {
                Intent intent = new Intent(Utils.getContext(), Utils.getMainActivity());
                intent.putExtra("LoginOut", true);
                Utils.getContext().startActivity(intent);
            }
        }
    }

    public abstract Type getType();

    public abstract void onSuccess(Object data);

    public void onError(String error_code, String error_message) {

    }

    public boolean enableShowToast() {
        return true;
    }

    public void onFinish() {
    }
}
