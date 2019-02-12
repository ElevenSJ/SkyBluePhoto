package com.sj.skyblue.utils;

import android.app.Activity;
import android.content.Context;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Sunj on 2018/12/9.
 */

public class PromptDialogUtil {
    private PromptDialog promptDialog;
    private static final PromptDialogUtil ourInstance = new PromptDialogUtil();

    public static PromptDialogUtil getInstance() {
        return ourInstance;
    }

    private PromptDialogUtil() {
    }
    public void init(Context mContext){
        if (mContext instanceof Activity){
            promptDialog = new PromptDialog((Activity) mContext);
        }
    }

    public PromptDialog getPromptDialog() {
        return promptDialog;
    }
}
