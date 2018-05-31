package com.kco.fun.demo2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.kco.fun.R;

/**
 * Created by 666666 on 2018/5/31.
 */
public class FilterListViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public FilterListViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.filterName);
    }
}
