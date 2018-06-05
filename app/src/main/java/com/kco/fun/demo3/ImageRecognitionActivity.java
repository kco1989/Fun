package com.kco.fun.demo3;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.kco.fun.R;
import com.kco.fun.demo2.PhotoConfig;
import com.kco.fun.demo3.adapter.ButtonListAdapter;
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
        Log.d(TAG, "onCreate");
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        Log.d(TAG, result.getImage().getOriginalPath());
        showImg(result.getImage());

        LinearLayoutManager filterListLayoutManager = new LinearLayoutManager(this);
        filterListLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        btnIRList.setLayoutManager(filterListLayoutManager);
        btnIRList.setAdapter(new ButtonListAdapter(this));
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    private void showImg(TImage image) {
        Log.d(TAG, "showImg --> " + image.getOriginalPath());
        PictureUtils.compress(this, 1024, new File(image.getOriginalPath()), new OnCompressListener() {
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
        switch (view.getId()){
            case R.id.btnIRPickByTake:
                TakePhotoUtils.pickBySelect(this, photoConfig, false);
                break;
            case R.id.btnIRPickBySelect:
                TakePhotoUtils.pickBySelect(this, photoConfig, true);
                break;
            default:
                break;
        }
    }

}
