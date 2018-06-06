package com.kco.fun.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kco.fun.R;
import com.kco.fun.activity.demo3.ImageRecognitionActivity;
import com.kco.fun.activity.demo4.OcrRecognitionActivity;
import com.kco.fun.bean.ImageRecognitionBean;
import com.kco.fun.bean.OcrRecognitionBean;
import com.kco.fun.bean.ResultBean;
import com.kco.fun.enums.ImageRecognitionEnum;
import com.kco.fun.enums.OcrRecognitionEnum;
import com.kco.fun.tools.RxTencentAiTools;

import io.reactivex.functions.Consumer;

/**
 * Created by 666666 on 2018/5/31.
 */
public class OcrRecognitionListAdapter extends RecyclerView.Adapter<OcrRecognitionListViewHolder> {
    private static final String TAG = "OcrRecogAdapter";
    private OcrRecognitionActivity context;
    private OcrRecognitionEnum[] filterBeanList = OcrRecognitionEnum.values();
    public OcrRecognitionListAdapter(OcrRecognitionActivity context) {
        this.context = context;
        Log.d(TAG, "FilterListAdapter");
    }

    @Override
    public OcrRecognitionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        return new OcrRecognitionListViewHolder(LayoutInflater.from(context).inflate(R.layout.ocr_recognition_list, parent,false));
    }

    @Override
    public void onBindViewHolder(OcrRecognitionListViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        final OcrRecognitionEnum ocrRecognitionEnum = filterBeanList[position];
        holder.ocrRecognitionBtn.setText(ocrRecognitionEnum.getName());
        Log.d(TAG, ocrRecognitionEnum.getName());
        holder.ocrRecognitionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "setOnClickListener -> " + ocrRecognitionEnum.getName());
                RxTencentAiTools.orcRecognition(ocrRecognitionEnum, context.imageFile, new Consumer<ResultBean<OcrRecognitionBean>>() {
                    @Override
                    public void accept(ResultBean<OcrRecognitionBean> ocrRecognitionBeanResultBean) throws Exception {
                        String text = ocrRecognitionBeanResultBean.getData().getText();
                        context.tvOcrText.setText(text);
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
