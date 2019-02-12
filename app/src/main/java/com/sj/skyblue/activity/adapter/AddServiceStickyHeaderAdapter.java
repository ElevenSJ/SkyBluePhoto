package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.decoration.StickyHeaderDecoration;
import com.sj.skyblue.R;
import com.sj.skyblue.entity.ServiceEntity;

/**
 * Created by Sunj on 2018/11/16.
 */

public class AddServiceStickyHeaderAdapter implements StickyHeaderDecoration.IStickyHeaderAdapter<AddServiceStickyHeaderAdapter.HeaderHolder> {
    private LayoutInflater mInflater;
    AddServiceRyvAdapter mAdapter;

    public AddServiceStickyHeaderAdapter(Context context, AddServiceRyvAdapter mAdapter) {
        mInflater = LayoutInflater.from(context);
        this.mAdapter = mAdapter;
    }

    @Override
    public long getHeaderId(int position) {
        if (mAdapter.getItem(position) instanceof ServiceEntity) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.header_item, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        viewholder.header.setText(getHeaderId(position) == 0 ? "选择服务" : "选择商品");
    }

    class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView;
        }
    }
}
