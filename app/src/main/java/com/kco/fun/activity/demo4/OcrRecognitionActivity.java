package com.kco.fun.activity.demo4;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;
import com.kco.fun.R;
import com.kco.fun.adapter.OcrRecognitionListAdapter;
import com.kco.fun.bean.PhotoConfig;
import com.kco.fun.tools.PictureUtils;
import com.kco.fun.tools.TakePhotoUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zibin.luban.OnCompressListener;

/**
 * Created by 666666 on 2018/6/4.
 */

public class OcrRecognitionActivity extends TakePhotoActivity {
    private static final String TAG = "OcrRecognition";
    private PhotoConfig photoConfig = new PhotoConfig();
    public File imageFile;

    @BindView(R.id.btnOcrList)
    public RecyclerView btnOrcList;

    @BindView(R.id.tvOcrText)
    public TextView tvOcrText;

    @BindView(R.id.imageViewOcr)
    public ImageView imageViewOcr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ocr_recognition_layout);
        ButterKnife.bind(this);

        LinearLayoutManager filterListLayoutManager = new LinearLayoutManager(this);
        filterListLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        btnOrcList.setLayoutManager(filterListLayoutManager);
        btnOrcList.setAdapter(new OcrRecognitionListAdapter(this));
        Log.d(TAG, "onCreate");
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        Log.d(TAG, result.getImage().getOriginalPath());
        tvOcrText.setText("");
        PictureUtils.compress(this, 1024, new File(result.getImage().getOriginalPath()), new OnCompressListener() {
            @Override
            public void onStart() {
                System.out.println("onStart");
            }

            @Override
            public void onSuccess(File file) {
                imageFile = file;
                Glide.with(OcrRecognitionActivity.this).load(file).into(imageViewOcr);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }
        });
    }


    @OnClick({R.id.btnOcrPickBySelect, R.id.btnOcrPickByTake})
    public void pick(View view){
        TakePhotoUtils.pickBySelect(this, photoConfig, view.getId() == R.id.btnOcrPickBySelect);
    }

}
