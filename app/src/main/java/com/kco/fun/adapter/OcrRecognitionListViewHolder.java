package com.kco.fun.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.kco.fun.R;

/**
 * Created by 666666 on 2018/5/31.
 */
public class OcrRecognitionListViewHolder extends RecyclerView.ViewHolder {

    public Button ocrRecognitionBtn;
    public OcrRecognitionListViewHolder(View itemView) {
        super(itemView);
        ocrRecognitionBtn = itemView.findViewById(R.id.ocrRecognitionBtn);
    }
}
