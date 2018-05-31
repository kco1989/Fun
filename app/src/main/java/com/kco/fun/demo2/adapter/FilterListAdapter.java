package com.kco.fun.demo2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.kco.fun.R;

import java.util.List;

/**
 * Created by 666666 on 2018/5/31.
 */
public class FilterListAdapter extends ArrayAdapter<FilterBean> {
    private int resourceId;
    public FilterListAdapter(Context context) {
        super(context, R.layout.filter_list, FilterBean.getList());
        this.resourceId = R.layout.filter_list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FilterBean filterBean = (FilterBean) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView textView = view.findViewById(R.id.filterName);
        textView.setText(filterBean.getName());
        return textView;
    }
}
