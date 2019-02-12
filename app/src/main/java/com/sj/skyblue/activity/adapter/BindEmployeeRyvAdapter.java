package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.skyblue.R;
import com.sj.skyblue.entity.EmployeeBase;
import com.sj.skyblue.entity.GoodEntity;
import com.sj.skyblue.entity.base.ResEntity;

/**
 * Created by Sunj on 2018/7/8.
 */

public class BindEmployeeRyvAdapter extends RecyclerArrayAdapter<EmployeeBase> {

    public BindEmployeeRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindEmployeeRyvHolder(parent);
    }

    public static class BindEmployeeRyvHolder extends BaseViewHolder<EmployeeBase> {
        private LinearLayout mainLayout;
        private TextView tvName;

        public BindEmployeeRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_service);
            mainLayout = $(R.id.item_layout);
            tvName = $(R.id.tv_name);
        }

        @Override
        public void setData(final EmployeeBase data) {
            super.setData(data);
            tvName.setText(data.getName());
            if (data.isSelected()) {
                tvName.setTextColor(mainLayout.getContext().getResources().getColor(R.color.white));
                mainLayout.setBackgroundResource(R.drawable.gradient_orange);
            } else {
                tvName.setTextColor(mainLayout.getContext().getResources().getColor(R.color.black));
                mainLayout.setBackgroundResource(R.color.bg_gray);
            }
        }
    }
}
