package com.sj.skyblue.lisenter;

/**
 * Created by Sunj on 2018/11/18.
 */

public interface AdapterItemDoLisenter {
    int TYPE_DELETE = 1;
    int TYPE_PAYBACK = 2;
    int TYPE_UPDATE = 3;
    int TYPE_CHANGE = 4;
    void doItem(int type,int position);
}
