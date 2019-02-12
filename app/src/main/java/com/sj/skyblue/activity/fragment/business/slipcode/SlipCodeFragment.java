package com.sj.skyblue.activity.fragment.business.slipcode;

import android.os.Bundle;

import com.sj.skyblue.R;
import com.sj.skyblue.activity.base.BaseFragment;

/**
 * Created by Sunj on 2018/11/4.
 */

public class SlipCodeFragment extends BaseFragment {
    public static SlipCodeFragment newInstance() {
        Bundle args = new Bundle();
        SlipCodeFragment fragment = new SlipCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_slip_code;
    }
}
