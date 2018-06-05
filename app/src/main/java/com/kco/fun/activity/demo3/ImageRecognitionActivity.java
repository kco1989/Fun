package com.kco.fun.activity.demo3;

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
import com.kco.fun.bean.PhotoConfig;
import com.kco.fun.adapter.ImageRecognitionListAdapter;
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

public class ImageRecognitionActivity extends TakePhotoActivity {
    private static final String TAG = "ImageRecognition";
    private PhotoConfig photoConfig = new PhotoConfig();
    public File imageFile;

    @BindView(R.id.btnIRList)
    public RecyclerView btnIRList;

    @BindView(R.id.tvIRText)
    public TextView textView;

    @BindView(R.id.imageViewIR)
    public ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_recognition_layout);
        ButterKnife.bind(this);

        LinearLayoutManager filterListLayoutManager = new LinearLayoutManager(this);
        filterListLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        btnIRList.setLayoutManager(filterListLayoutManager);
        btnIRList.setAdapter(new ImageRecognitionListAdapter(this));
        Log.d(TAG, "onCreate");
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        Log.d(TAG, result.getImage().getOriginalPath());
        PictureUtils.compress(this, 1024, new File(result.getImage().getOriginalPath()), new OnCompressListener() {
            @Override
            public void onStart() {
                System.out.println("onStart");
            }

            @Override
            public void onSuccess(File file) {
                imageFile = file;
                Glide.with(ImageRecognitionActivity.this).load(file).into(imageView);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }
        });
    }


    @OnClick({R.id.btnIRPickByTake, R.id.btnIRPickBySelect})
    public void pick(View view){
        TakePhotoUtils.pickBySelect(this, photoConfig, view.getId() == R.id.btnIRPickBySelect);
    }

}
