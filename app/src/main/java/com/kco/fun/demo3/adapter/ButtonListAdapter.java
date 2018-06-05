package com.kco.fun.demo3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kco.fun.R;
import com.kco.fun.demo2.adapter.FilterBean;
import com.kco.fun.demo2.adapter.ImageListAdapter;
import com.kco.fun.demo3.ImageRecognitionActivity;
import com.kco.fun.tools.RxTencentAiTools;
import com.kco.fun.tools.bean.ImageRecognitionBean;
import com.kco.fun.tools.bean.ResultBean;
import com.kco.fun.tools.enums.ImageRecognitionEnum;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by 666666 on 2018/5/31.
 */
public class ButtonListAdapter extends RecyclerView.Adapter<ButtonListViewHolder> {
    private static final String TAG = "FilterListAdapter";
    private ImageRecognitionActivity context;
    private ImageRecognitionEnum[] filterBeanList = ImageRecognitionEnum.values();
    public ButtonListAdapter(ImageRecognitionActivity context) {
        this.context = context;
        Log.d(TAG, "FilterListAdapter");
    }

    @Override
    public ButtonListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        return new ButtonListViewHolder(LayoutInflater.from(context).inflate(R.layout.filter_list, parent,false));
    }

    @Override
    public void onBindViewHolder(ButtonListViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        final ImageRecognitionEnum imageRecognitionEnum = filterBeanList[position];
        holder.filterBtn.setText(imageRecognitionEnum.getName());
        Log.d(TAG, imageRecognitionEnum.getName());
        holder.filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "setOnClickListener -> " + imageRecognitionEnum.getName());
                RxTencentAiTools.imageRecognition(imageRecognitionEnum, context.imageFile, new Consumer<ResultBean<ImageRecognitionBean>>() {
                    @Override
                    public void accept(ResultBean<ImageRecognitionBean> imageRecognitionBeanResultBean) throws Exception {
                        String text = imageRecognitionBeanResultBean.getData().getText();
                        context.textView.setText(text);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterBeanList.length;
    }

}
