package com.kco.fun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kco.fun.R;
import com.kco.fun.bean.FilterBean;

import java.util.List;

/**
 * Created by 666666 on 2018/5/31.
 */
public class FilterListAdapter extends RecyclerView.Adapter<FilterListViewHolder> {
    private static final String TAG = "FilterListAdapter";
    private Context context;
    private List<FilterBean> filterBeanList = FilterBean.getList();
    private ImageListAdapter imageListAdapter;
    public FilterListAdapter(Context context, ImageListAdapter imageListAdapter) {
        this.context = context;
        this.imageListAdapter = imageListAdapter;
        Log.d(TAG, "FilterListAdapter");
    }

    @Override
    public FilterListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        return new FilterListViewHolder(LayoutInflater.from(context).inflate(R.layout.filter_list, parent,false));
    }

    @Override
    public void onBindViewHolder(FilterListViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        final FilterBean filterBean = filterBeanList.get(position);
        holder.filterBtn.setText(filterBean.getPhotoBeautifyEnum().getDescribe());
        Log.d(TAG, filterBean.getPhotoBeautifyEnum().getDescribe());
        holder.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "setOnClickListener -> " + filterBean.getPhotoBeautifyEnum().getDescribe());
                imageListAdapter.setPhotoBeautifyEnum(filterBean.getPhotoBeautifyEnum());

            }
        });
    }

    @Override
    public int getItemCount() {
        return filterBeanList.size();
    }

}
