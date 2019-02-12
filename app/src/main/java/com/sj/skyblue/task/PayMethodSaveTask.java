package com.sj.skyblue.task;

import android.content.Context;
import android.os.AsyncTask;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.Utils;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.PayMethod;
import com.sj.skyblue.entity.UserInfoEntity;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:序列化支付方式
 */
public class PayMethodSaveTask extends AsyncTask<List<PayMethod>, Integer, Boolean> {

    @Override
    protected Boolean doInBackground( List<PayMethod>... payMethods) {
        //序列化到本地
        try {
            FileOutputStream outStream = Utils.getContext().openFileOutput(Constants.PAY_METHOD_FILE_NAME,
                    Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(outStream);
            out.writeObject(payMethods[0]);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
            return false;
        }
        return true;
    }
}
