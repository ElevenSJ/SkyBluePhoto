package com.sj.skyblue.activity.fragment.business.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sj.skyblue.R;
import com.sj.skyblue.activity.base.BaseFragment;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * Created by Sunj on 2018/11/4.
 */

public class SettingContentFragment extends BaseFragment {

    private int index = 0;
    public static final int FIRST = 0;
    public static final int SECOND = 1;

    private SupportFragment[] mFragments = new SupportFragment[3];

    public static SettingContentFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("index", position);
        SettingContentFragment fragment = new SettingContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        index = getArguments().getInt("index");
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_setting_content;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(SettingPrintFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = SettingPrintFragment.newInstance();
            mFragments[SECOND] = SettingAboutFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_content_container, index,
                    mFragments[FIRST],
                    mFragments[SECOND]);
        } else {
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(SettingAboutFragment.class);
        }
    }
}