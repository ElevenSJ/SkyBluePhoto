package com.sj.skyblue.activity.fragment.business.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sj.skyblue.R;
import com.sj.skyblue.activity.base.BaseFragment;
import com.sj.skyblue.manager.SPManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Sunj on 2018/11/4.
 */

public class SettingPrintFragment extends BaseFragment {


    @BindView(R.id.et_title)
    EditText etTitle;

    public static SettingPrintFragment newInstance() {
        SettingPrintFragment fragment = new SettingPrintFragment();
        return fragment;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_setting_print;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        etTitle.setText(SPManager.getInstance().getPrintTitle());
        etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SPManager.getInstance().setPrintTitle(s.toString());
            }
        });
    }

}