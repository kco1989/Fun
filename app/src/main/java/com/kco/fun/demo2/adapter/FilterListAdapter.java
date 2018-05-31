package com.kco.fun.demo2.adapter;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kco.fun.R;

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
        holder.filterBtn.setText(filterBean.getName());
        Log.d(TAG, filterBean.getName());
        holder.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "setOnClickListener -> " + filterBean.getName());
                imageListAdapter.setImageList(filterBean.getImages(), filterBean.getType());

            }
        });
    }

    @Override
    public int getItemCount() {
        return filterBeanList.size();
    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        FilterBean filterBean = (FilterBean) getItem(position);
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.filter_list, null);
//        TextView textView = view.findViewById(R.id.filterName);
//        textView.setText(filterBean.getName());
//        textView.setTag(filterBean);
//        return textView;
//    }

}
