package com.sj.skyblue.task;

import android.os.AsyncTask;

import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.Utils;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.UserInfoEntity;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * 创建时间: on 2018/4/3.
 * 创建人: 孙杰
 * 功能描述:序列化用户信息到本地
 */
public class UserInfoGetTask extends AsyncTask<Integer, Integer, UserInfoEntity> {

    @Override
    protected UserInfoEntity doInBackground(Integer... params) {
        //读取本地序列化
        UserInfoEntity user;
        try {
            FileInputStream inputStream = Utils.getContext().openFileInput(Constants.USER_INFO_FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            user = (UserInfoEntity) in.readObject();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
            return null;
        }
        return user;
    }
}
