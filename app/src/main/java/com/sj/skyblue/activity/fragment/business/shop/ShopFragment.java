package com.sj.skyblue.activity.fragment.business.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.CompoundButton;

import com.alibaba.fastjson.TypeReference;
import com.guyj.CommonAdapter;
import com.guyj.MultiItemTypeAdapter;
import com.guyj.base.ViewHolder;
import com.guyj.listener.EasyOnItemChildCheckChangeListener;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.sj.module_lib.http.BaseResponse;
import com.sj.module_lib.http.ServerResultBack;
import com.sj.module_lib.utils.DisplayUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.adapter.ShopRyvAdapter;
import com.sj.skyblue.activity.adapter.itemdecoration.SpaceItemDecoration;
import com.sj.skyblue.activity.base.BaseFragment;
import com.sj.skyblue.entity.AppointmentEntity;
import com.sj.skyblue.entity.GoodEntity;
import com.sj.skyblue.entity.base.BaseDataList;
import com.sj.skyblue.http.API;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Sunj on 2018/11/4.
 */

public class ShopFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    ShopRyvAdapter mAdapter;
    final int SPANCOUNT = 8;


    public static ShopFragment newInstance() {
        Bundle args = new Bundle();
        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_shop;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(_mActivity, SPANCOUNT, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(gridLayoutManager);
        rylView.addItemDecoration(new SpaceItemDecoration(DisplayUtils.dip2px(_mActivity, 20), 0, true, SpaceItemDecoration.GRIDLAYOUT));
        rylView.setRefreshListener(this);
        mAdapter = new ShopRyvAdapter(_mActivity);
        rylView.setAdapter(mAdapter);
        rylView.showEmpty();

        post(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        });
    }

    @Override
    public void onRefresh() {
        promptDialog.showLoading("正在获取数据");
        API.queryGoodList(new ServerResultBack() {
            @Override
            public void onSuccess(Object data) {
                List<GoodEntity> result = (List<GoodEntity>) data;
                mAdapter.addAll(result);
            }

            @Override
            public Type getType() {
                return new TypeReference<BaseResponse<List<GoodEntity>>>() {
                }.getType();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                promptDialog.dismiss();
            }
        });
    }
}
