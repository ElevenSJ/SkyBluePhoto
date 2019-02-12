package com.sj.skyblue.task;

import android.content.Context;
import android.os.AsyncTask;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.Utils;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.UserInfoEntity;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:序列化用户信息到本地
 */
public class UserInfoSaveTask extends AsyncTask<UserInfoEntity, Integer, Boolean> {

    @Override
    protected Boolean doInBackground(UserInfoEntity... userInfoBeans) {
        //序列化到本地
        try {
            FileOutputStream outStream = Utils.getContext().openFileOutput(Constants.USER_INFO_FILE_NAME,
                    Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(outStream);
            out.writeObject(userInfoBeans[0]);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
            return false;
        }
        return true;
    }
}
