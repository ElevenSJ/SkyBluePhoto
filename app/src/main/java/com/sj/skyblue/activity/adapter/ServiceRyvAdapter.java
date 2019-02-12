package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.utils.StringUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.entity.GoodEntity;
import com.sj.skyblue.entity.ServiceEntity;
import com.sj.skyblue.entity.base.ResEntity;

/**
 * Created by Sunj on 2018/7/8.
 */

public class ServiceRyvAdapter extends RecyclerArrayAdapter<ServiceEntity> {

    public ServiceRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ServiceRyvHolder(parent);
    }

    public static class ServiceRyvHolder extends BaseViewHolder<ServiceEntity> {
        private LinearLayout mainLayout;
        private TextView tvName;


        public ServiceRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_service);
            mainLayout = $(R.id.item_layout);
            tvName= $(R.id.tv_name);
        }

        @Override
        public void setData(final ServiceEntity data) {
            super.setData(data);
            tvName.setText(data.getName());
            if (data.isSelected()){
                tvName.setTextColor(mainLayout.getContext().getResources().getColor(R.color.white));
                mainLayout.setBackgroundResource(R.drawable.gradient_orange);
            }else{
                tvName.setTextColor(mainLayout.getContext().getResources().getColor(R.color.black));
                mainLayout.setBackgroundResource(R.color.bg_gray);
            }
        }
    }
}
