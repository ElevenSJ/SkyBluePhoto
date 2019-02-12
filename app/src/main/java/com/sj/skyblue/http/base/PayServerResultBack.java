package com.sj.skyblue.http.base;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.jady.retrofitclient.callback.HttpCallback;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.ToastUtils;

/**
 * Created by Sunj on 2018/7/7.
 */
public abstract class PayServerResultBack<T, V> extends HttpCallback<T> {
    @Override
    public void onResolve(T t) {
        Logger.d(JSON.toJSONString(t));
        BasePayResponse<V> callbackData = (BasePayResponse) t;
        if (callbackData.getResult_code().equals("200")) {
            V result = callbackData.getBiz_response();
            this.onSuccess(result);
        } else {
            onFailed(callbackData.getResult_code(), callbackData.getError_message());
        }
    }

    @Override
    public void onFailed(String error_code, String error_message) {
        if (!TextUtils.isEmpty(error_message) && error_message.contains("BEGIN_OBJECT")) {
            onError("-100", "数据解析异常");
            return;
        }
        onError(error_code, error_message);
    }

    public abstract void onSuccess(V data);

    public abstract void onError(String error_code, String error_message);
}
