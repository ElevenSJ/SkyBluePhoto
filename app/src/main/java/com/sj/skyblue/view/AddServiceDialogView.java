package com.sj.skyblue.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.StickyHeaderDecoration;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.utils.DisplayUtils;
import com.sj.module_lib.utils.StringUtils;
import com.sj.module_lib.utils.ToastUtils;
import com.sj.module_lib.utils.Utils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.adapter.AddServiceRyvAdapter;
import com.sj.skyblue.activity.adapter.AddServiceStickyHeaderAdapter;
import com.sj.skyblue.activity.adapter.ServiceRyvAdapter;
import com.sj.skyblue.activity.adapter.itemdecoration.SpaceItemDecoration;
import com.sj.skyblue.entity.GoodEntity;
import com.sj.skyblue.entity.ServiceEntity;
import com.sj.skyblue.entity.base.ResEntity;

import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by Sunj on 2018/11/12.
 */

public class AddServiceDialogView implements View.OnClickListener {
    ImageView imgClose;
    EasyRecyclerView rylView;
    EasyRecyclerView rylView1;
    Button btSure;
    Button btCancle;

    AddServiceRyvAdapter mAdapter;
    AddServiceRyvAdapter mAdapter1;
    final int SPANCOUNT = 6;

    private Activity activity;
    private View rootView;
    private AlertDialog dialog;
    private int type;

    private PromptDialog promptDialog;

    private int mCurrentPosition = -1;

    IServiceSelectLisenter iItemSelectLisenter;

    ServiceEntity serviceEntity;
    List<GoodEntity> goodEntityList = new ArrayList<>();

    public AddServiceDialogView(Activity activity, AlertDialog dialog, View view,int type) {
        this.activity = activity;
        this.rootView = view;
        this.dialog = dialog;
        this.type = type;
    }

    public void initModule(List<ServiceEntity> services, List<GoodEntity> products) {
        if (rootView == null) {
            Logger.e("not root exist");
            return;
        }
        promptDialog = new PromptDialog(activity);
        imgClose = rootView.findViewById(R.id.img_close);
        rylView = rootView.findViewById(R.id.ryl_view);
        rylView1 = rootView.findViewById(R.id.ryl_view1);
        btSure = rootView.findViewById(R.id.bt_sure);
        btCancle = rootView.findViewById(R.id.bt_cancle);


        rootView.findViewById(R.id.textView5).setVisibility(type==1?View.VISIBLE:View.INVISIBLE);
        rylView1.setVisibility(type==1?View.VISIBLE:View.INVISIBLE);

        imgClose.setOnClickListener(this);
        btCancle.setOnClickListener(this);
        btSure.setOnClickListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, SPANCOUNT, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(gridLayoutManager);
        rylView.addItemDecoration(new SpaceItemDecoration(DisplayUtils.dip2px(activity, 20), 0, true, SpaceItemDecoration.GRIDLAYOUT));
        mAdapter = new AddServiceRyvAdapter(activity);
        rylView.setAdapter(mAdapter);
        mAdapter.addAll(services);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mCurrentPosition != -1) {
                    mAdapter.getItem(mCurrentPosition).setSelected(false);
                    mAdapter.notifyItemChanged(mCurrentPosition);
                }
                if (mCurrentPosition!=position) {
                    mAdapter.getItem(position).setSelected(!mAdapter.getItem(position).isSelected());
                    mAdapter.notifyItemChanged(position);
                }
                if (mAdapter.getItem(position).isSelected()) {
                    serviceEntity = (ServiceEntity) mAdapter.getItem(position);
                    mCurrentPosition = position;
                } else {
                    serviceEntity = null;
                    mCurrentPosition = -1;
                }
            }
        });

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(activity, SPANCOUNT, LinearLayoutManager.VERTICAL, false);
        rylView1.setLayoutManager(gridLayoutManager1);
        rylView1.addItemDecoration(new SpaceItemDecoration(DisplayUtils.dip2px(activity, 20), 0, true, SpaceItemDecoration.GRIDLAYOUT));
        mAdapter1 = new AddServiceRyvAdapter(activity);
        rylView1.setAdapter(mAdapter1);
        mAdapter1.addAll(products);
        mAdapter1.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mAdapter1.getItem(position).isSelected()) {
                    goodEntityList.remove(mAdapter1.getItem(position));
                    mAdapter1.getItem(position).setSelected(false);
                } else {
                    goodEntityList.add((GoodEntity) mAdapter1.getItem(position));
                    mAdapter1.getItem(position).setSelected(true);
                }
                mAdapter1.notifyItemChanged(position);
            }
        });

    }

    private boolean checkEmpty() {
        boolean isOK = false;
        if (serviceEntity == null && goodEntityList.isEmpty()) {
            ToastUtils.showShortToast("请至少选择一项服务");
        } else {
            isOK = true;
        }
        return isOK;
    }

    public void setIServiceSelectLisenter(IServiceSelectLisenter selectLisenter) {
        this.iItemSelectLisenter = selectLisenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_close:
            case R.id.bt_cancle:
                dissmiss();
                break;
            case R.id.bt_sure:
                if (checkEmpty()) {
                    if (iItemSelectLisenter != null) {
                        iItemSelectLisenter.callback(serviceEntity, goodEntityList);
                    }
                    dissmiss();
                }
                break;
        }
    }

    private void dissmiss() {
        if (dialog != null) {
            dialog.dismiss();
            serviceEntity = null;
            mCurrentPosition = -1;
            goodEntityList.clear();
        }
    }


    public interface IServiceSelectLisenter {
        void callback(ServiceEntity resEntity, List<GoodEntity> goodEntityList);
    }

}
