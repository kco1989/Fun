package com.kco.fun.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kco.fun.R;
import com.kco.fun.activity.demo3.ImageRecognitionActivity;
import com.kco.fun.tools.RxTencentAiTools;
import com.kco.fun.bean.ImageRecognitionBean;
import com.kco.fun.bean.ResultBean;
import com.kco.fun.enums.ImageRecognitionEnum;

import io.reactivex.functions.Consumer;

/**
 * Created by 666666 on 2018/5/31.
 */
public class ImageRecognitionListAdapter extends RecyclerView.Adapter<ImageRecognitionListViewHolder> {
    private static final String TAG = "FilterListAdapter";
    private ImageRecognitionActivity context;
    private ImageRecognitionEnum[] filterBeanList = ImageRecognitionEnum.values();
    public ImageRecognitionListAdapter(ImageRecognitionActivity context) {
        this.context = context;
        Log.d(TAG, "FilterListAdapter");
    }

    @Override
    public ImageRecognitionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        return new ImageRecognitionListViewHolder(LayoutInflater.from(context).inflate(R.layout.image_recognition_list, parent,false));
    }

    @Override
    public void onBindViewHolder(ImageRecognitionListViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        final ImageRecognitionEnum imageRecognitionEnum = filterBeanList[position];
        holder.imageRecognitionBtn.setText(imageRecognitionEnum.getName());
        Log.d(TAG, imageRecognitionEnum.getName());
        holder.imageRecognitionBtn.setOnClickListener(new View.OnClickListener() {
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
