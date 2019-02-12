package com.sj.skyblue.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.decoration.StickyHeaderDecoration;
import com.sj.skyblue.R;

/**
 * Created by Sunj on 2018/12/1.
 */

public class StickyHeaderAdapter implements StickyHeaderDecoration.IStickyHeaderAdapter<StickyHeaderAdapter.HeaderHolder> {

    private LayoutInflater mInflater;

    public StickyHeaderAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public long getHeaderId(int position) {
        return position / 3;
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.header_item, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        viewholder.header.setText("第"+getHeaderId(position)+"组");
    }

    class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView;
        }
    }
}