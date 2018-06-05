package com.kco.fun.demo3.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.kco.fun.R;

/**
 * Created by 666666 on 2018/5/31.
 */
public class ButtonListViewHolder extends RecyclerView.ViewHolder {
    public Button filterBtn;
    public ButtonListViewHolder(View itemView) {
        super(itemView);
        filterBtn = itemView.findViewById(R.id.filterBtn);
    }
}
