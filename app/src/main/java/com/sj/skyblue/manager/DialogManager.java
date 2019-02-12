package com.sj.skyblue.manager;

import android.content.Context;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Sunj on 2018/11/4.
 */

public class DialogManager {

    PromptDialog promptDialog;

    private static final DialogManager ourInstance = new DialogManager();

    public static DialogManager getInstance() {
        return ourInstance;
    }

    private DialogManager() {
    }
    private void showWorkOpen(Context context, int id, String msg) {

    }
}
