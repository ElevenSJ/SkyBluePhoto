package com.sj.skyblue.activity.fragment.business.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.sj.skyblue.R;
import com.sj.skyblue.activity.adapter.SettingRyvAdapter;
import com.sj.skyblue.activity.base.BaseFragment;
import com.sj.skyblue.entity.SettingInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 预约记录列表页面
 * Created by Sunj on 2018/11/4.
 */

public class SettingListFragment extends BaseFragment {

    @BindView(R.id.ryl_view)
    EasyRecyclerView rylView;
    SettingRyvAdapter mAdapter;
    List<SettingInfo> settingInfoList = new ArrayList<>();

    private int mCurrentInxdex = -1;

    public static SettingListFragment newInstance() {
        Bundle args = new Bundle();
        SettingListFragment fragment = new SettingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_setting_list;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        settingInfoList.add(new SettingInfo(0, "打印模板设计"));
        settingInfoList.add(new SettingInfo(1, "版本信息"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        rylView.setLayoutManager(layoutManager);
        mAdapter = new SettingRyvAdapter(_mActivity);
        rylView.setAdapter(mAdapter);
        mAdapter.addAll(settingInfoList);

        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showContent(position);
            }
        });
        showContent(0);
    }

    private void showContent(int position) {
        Logger.d("showContent : id = " + position);
        if (mCurrentInxdex == position) {
            return;
        }
        if (mCurrentInxdex!=-1) {
            mAdapter.getItem(mCurrentInxdex).setSelected(false);
            mAdapter.notifyItemChanged(mCurrentInxdex);
        }
        mAdapter.getItem(position).setSelected(true);
        mAdapter.notifyItemChanged(position);
        mCurrentInxdex = position;
        SettingContentFragment fragment = SettingContentFragment.newInstance(position);
        ((SettingMainFragment) getParentFragment()).switchContentFragment(fragment);
    }

}
