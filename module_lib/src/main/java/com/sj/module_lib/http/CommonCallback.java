package com.sj.module_lib.http;

import android.content.Intent;
import android.support.annotation.Keep;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jady.retrofitclient.HttpManager;
import com.jady.retrofitclient.callback.HttpCallback;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.StringUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.module_lib.utils.Utils;


/**
 * 创建时间: on 2018/3/31.
 * 创建人: 孙杰
 * 功能描述:请求回调基类
 */
@Keep
public abstract class CommonCallback extends HttpCallback<String> {
    @Override
    public void onResolve(String json) {
        Logger.i("返回报文" + json);
        BaseResponse baseResponse = JSON.parseObject(json, BaseResponse.class);
        if (baseResponse.getCode().equals("1")) {
            onFinish();
            onSuccess(baseResponse.message);
        } else {
            onFailed(baseResponse.code, baseResponse.message);
        }
    }

    @Override
    public void onFailed(String error_code, String error_message) {
        onFinish();
        Logger.e("onFailed:error_code="+error_code);
        if (enableShowToast()) {
            ToastUtils.showShortToast(error_message);
        }
        //其他设备登录统一处理
        if (StringUtils.isEmpty(error_code)||error_code.equals("9")||error_code.equals("4")) {
            if (Utils.getMainActivity() != null) {
                Intent intent = new Intent(Utils.getContext(), Utils.getMainActivity());
                intent.putExtra("LoginOut", true);
                Utils.getContext().startActivity(intent);
            }
        }
    }

    public abstract void onSuccess(String message);

    public boolean enableShowToast() {
        return true;
    }

    public void onFinish() {
    }

}
