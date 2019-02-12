package com.sj.skyblue.task;

import android.os.AsyncTask;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.Utils;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.PayMethod;
import com.sj.skyblue.entity.UserInfoEntity;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:序列化用户信息到本地
 */
public class PayMethodGetTask extends AsyncTask<Integer, Integer, List<PayMethod>> {

    @Override
    protected  List<PayMethod> doInBackground(Integer... params) {
        //读取本地序列化
        List<PayMethod> payMethods;
        try {
            FileInputStream inputStream = Utils.getContext().openFileInput(Constants.PAY_METHOD_FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            payMethods = ( List<PayMethod>) in.readObject();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
            return null;
        }
        return payMethods;
    }
}
