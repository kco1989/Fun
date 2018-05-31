package com.kco.fun.demo2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 666666 on 2018/5/31.
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListViewHolder> {

    public List<String> imageList;
    Context context;
    public ImageListAdapter(Context context) {
        this.context = context;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    public List<String> getImageList() {
        return imageList;
    }

    @Override
    public ImageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageListViewHolder(LayoutInflater.from(context).inflate(R.layout.image_list, parent,false));
    }

    @Override
    public void onBindViewHolder(ImageListViewHolder holder, int position) {
        if (imageList == null){
            return;
        }
        String image = imageList.get(position);
        Glide.with(context).load(image).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList == null ? 0 : imageList.size();
    }

}
