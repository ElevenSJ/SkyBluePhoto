package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.sj.module_lib.utils.StringUtils;
import com.sj.skyblue.R;
import com.sj.skyblue.constant.Constants;
import com.sj.skyblue.entity.OrderItem;
import com.sj.skyblue.entity.SettingInfo;
import com.sj.skyblue.event.OrderCreateEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Sunj on 2018/7/8.
 */

public class SettingRyvAdapter extends RecyclerArrayAdapter<SettingInfo> {

    public SettingRyvAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SettingRyvHolder(parent);
    }

    public static class SettingRyvHolder extends BaseViewHolder<SettingInfo> {
        LinearLayout mainLayout;
        TextView tvDo;


        public SettingRyvHolder(ViewGroup parent) {
            super(parent, R.layout.item_setting);
            mainLayout = $(R.id.item_layout);
            tvDo = $(R.id.tv_name);
        }

        @Override
        public void setData(final SettingInfo data) {
            super.setData(data);
            mainLayout.setBackgroundColor(getAdapterPosition() % 2 == 0 ? mainLayout.getContext().getResources().getColor(R.color.bg_gray) : mainLayout.getContext().getResources().getColor(R.color.divider_color));
            tvDo.setText(data.getName());
            if (data.isSelected()) {
                tvDo.setTextColor(mainLayout.getContext().getResources().getColor(R.color.white));
                mainLayout.setBackgroundResource(R.drawable.gradient_orange);
            } else {
                tvDo.setTextColor(mainLayout.getContext().getResources().getColor(R.color.black_alpha80));
                mainLayout.setBackgroundColor(getAdapterPosition() % 2 == 0 ? mainLayout.getContext().getResources().getColor(R.color.bg_gray) : mainLayout.getContext().getResources().getColor(R.color.divider_color));
            }
        }
    }
}
