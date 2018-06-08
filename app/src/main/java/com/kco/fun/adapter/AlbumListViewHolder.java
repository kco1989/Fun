package com.kco.fun.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kco.fun.R;

/**
 * Created by 666666 on 2018/5/31.
 */
public class AlbumListViewHolder extends RecyclerView.ViewHolder {
    public ImageView albumIV;
    public TextView albumTV;
    public AlbumListViewHolder(View itemView) {
        super(itemView);
        albumIV = itemView.findViewById(R.id.albumIV);
        albumTV = itemView.findViewById(R.id.albumTV);
    }
}
