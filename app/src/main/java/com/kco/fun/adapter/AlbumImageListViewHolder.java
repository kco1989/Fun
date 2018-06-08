package com.kco.fun.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kco.fun.R;

/**
 * Created by 666666 on 2018/5/31.
 */
public class AlbumImageListViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public AlbumImageListViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image_item);
    }
}
