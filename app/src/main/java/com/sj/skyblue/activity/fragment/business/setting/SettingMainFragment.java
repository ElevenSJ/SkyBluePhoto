package com.sj.skyblue.activity.fragment.business.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sj.skyblue.R;
import com.sj.skyblue.activity.base.BaseFragment;
import com.sj.skyblue.activity.fragment.business.order.OrderContentFragment;
import com.sj.skyblue.activity.fragment.business.order.OrderListFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Sunj on 2018/11/4.
 */

public class SettingMainFragment extends BaseFragment {
    public static SettingMainFragment newInstance() {
        Bundle args = new Bundle();
        SettingMainFragment fragment = new SettingMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_setting;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(SettingListFragment.class) == null) {
            SettingListFragment settingListFragment = SettingListFragment.newInstance();
            loadRootFragment(R.id.fl_list_container, settingListFragment,false, true);
        }
    }

    /**
     * 替换加载 内容Fragment
     *
     * @param fragment
     */
    public void switchContentFragment(SettingContentFragment fragment) {
        SupportFragment contentFragment = findChildFragment(SettingContentFragment.class);
        if (contentFragment != null) {
            contentFragment.replaceFragment(fragment, false);
        }else{
            loadRootFragment(R.id.fl_content_container, fragment, false, true);
        }
    }
}
