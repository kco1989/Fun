package com.kco.fun.demo2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.*;
import com.kco.fun.R;
import com.kco.fun.demo1.ResultActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by 666666 on 2018/5/31.
 */
public class PhotoActivity extends TakePhotoActivity {
    private static final String TAG = "PhotoActivity";
    private PhotoConfig photoConfig = new PhotoConfig();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phtoto_layout);
        ButterKnife.bind(this);
    }


    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        Log.d(TAG, "single -> " + result.getImage().getOriginalPath());
        for (TImage image : result.getImages()){
            Log.d(TAG, "bat ->" + image.getOriginalPath());
        }
        showImg(result.getImages());
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    private void showImg(ArrayList<TImage> images) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("images", images);
        startActivity(intent);
    }


    @OnClick({R.id.btnPickByTake, R.id.btnPickBySelect})
    public void pick(View view){
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        TakePhoto takePhoto = getTakePhoto();
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        switch (view.getId()) {
            case R.id.btnPickBySelect:
                int limit = photoConfig.getLimit();
                if (limit > 1) {
                    if (photoConfig.isCrop()) {
                        takePhoto.onPickMultipleWithCrop(limit, getCropOptions());
                    } else {
                        takePhoto.onPickMultiple(limit);
                    }
                    return;
                }
                if (photoConfig.isFrom()) {
                    if (photoConfig.isCrop()) {
                        takePhoto.onPickFromDocumentsWithCrop(imageUri, getCropOptions());
                    } else {
                        takePhoto.onPickFromDocuments();
                    }
                    return;
                } else {
                    if (photoConfig.isCrop()) {
                        takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                    } else {
                        takePhoto.onPickFromGallery();
                    }
                }
                break;
            case R.id.btnPickByTake:
                if (photoConfig.isCrop()) {
                    takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                } else {
                    takePhoto.onPickFromCapture(imageUri);
                }
                break;
            default:
                break;
        }
    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        if (photoConfig.isPickTool()) {
            builder.setWithOwnGallery(true);
        }
        if (photoConfig.isCorrectTool()) {
            builder.setCorrectImage(true);
        }
        takePhoto.setTakePhotoOptions(builder.create());

    }

    private void configCompress(TakePhoto takePhoto) {
        if (photoConfig.isCompress()) {
            takePhoto.onEnableCompress(null, false);
            return;
        }
        int maxSize = photoConfig.getCompressSize();
        int width = photoConfig.getCompressWidth();
        int height = photoConfig.getCompressHeight();
        boolean showProgressBar = photoConfig.isShowProgressBar();
        boolean enableRawFile = photoConfig.isRawFile();
        CompressConfig config;
        if (photoConfig.isCompressTool()) {
            config = new CompressConfig.Builder().setMaxSize(maxSize)
                    .setMaxPixel(width >= height ? width : height)
                    .enableReserveRaw(enableRawFile)
                    .create();
        } else {
            LubanOptions option = new LubanOptions.Builder().setMaxHeight(height).setMaxWidth(width).setMaxSize(maxSize).create();
            config = CompressConfig.ofLuban(option);
            config.enableReserveRaw(enableRawFile);
        }
        takePhoto.onEnableCompress(config, showProgressBar);


    }

    private CropOptions getCropOptions() {
        if (photoConfig.isCrop()) {
            return null;
        }
        int height = photoConfig.getCropHeight();
        int width = photoConfig.getCropWidth();
        boolean withWonCrop = photoConfig.isCropTool();

        CropOptions.Builder builder = new CropOptions.Builder();

        if (photoConfig.isCropSize()) {
            builder.setAspectX(width).setAspectY(height);
        } else {
            builder.setOutputX(width).setOutputY(height);
        }
        builder.setWithOwnCrop(withWonCrop);
        return builder.create();
    }

}
