package com.kco.fun.demo2;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.*;
import com.kco.fun.R;
import com.kco.fun.demo2.adapter.FilterListAdapter;
import com.kco.fun.demo2.adapter.ImageListAdapter;

import java.io.File;

/**
 * Created by 666666 on 2018/5/31.
 */
public class PhotoActivity extends TakePhotoActivity {
    private static final String TAG = "PhotoActivity";
    private PhotoConfig photoConfig = new PhotoConfig();
    @BindView(R.id.imageView)
    public ImageView imageView;
    @BindView(R.id.filterList)
    public RecyclerView filterList;
    @BindView(R.id.imageList)
    public RecyclerView imageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phtoto_layout);
        ButterKnife.bind(this);

        ImageListAdapter imageListAdapter = new ImageListAdapter(this);
        FilterListAdapter filterListAdapter = new FilterListAdapter(this, imageListAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        filterList.setLayoutManager(layoutManager);
        filterList.setAdapter(filterListAdapter);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        imageList.setLayoutManager(layoutManager1);
        imageList.setAdapter(imageListAdapter);
        Log.d(TAG, "onCreate");

    }

    @OnClick(R.id.btnSumbit)
    public void sumbit(View view){

    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        showImg(result.getImage());
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
        Glide.with(this).load(new File(image.getOriginalPath())).into(imageView);
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
