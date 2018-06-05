package com.kco.fun.tools;

import android.net.Uri;
import android.os.Environment;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TakePhotoOptions;
import com.kco.fun.bean.PhotoConfig;

import java.io.File;

/**
 * Created by 666666 on 2018/6/5.
 */

public final class TakePhotoUtils {

    public static void pickBySelect(TakePhotoActivity content, PhotoConfig photoConfig, boolean isSelect){
        File file = new File(content.getExternalFilesDir(Environment.DIRECTORY_PICTURES), System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        TakePhoto takePhoto = content.getTakePhoto();
        configCompress(takePhoto, photoConfig);
        configTakePhotoOption(takePhoto, photoConfig);
        if (isSelect){
            int limit = photoConfig.getLimit();
            if (limit > 1) {
                if (photoConfig.isCrop()) {
                    takePhoto.onPickMultipleWithCrop(limit, getCropOptions(photoConfig));
                } else {
                    takePhoto.onPickMultiple(limit);
                }
                return;
            }
            if (photoConfig.isFrom()) {
                if (photoConfig.isCrop()) {
                    takePhoto.onPickFromDocumentsWithCrop(imageUri, getCropOptions(photoConfig));
                } else {
                    takePhoto.onPickFromDocuments();
                }
                return;
            } else {
                if (photoConfig.isCrop()) {
                    takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions(photoConfig));
                } else {
                    takePhoto.onPickFromGallery();
                }
            }
        }else {
            if (photoConfig.isCrop()) {
                takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions(photoConfig));
            } else {
                takePhoto.onPickFromCapture(imageUri);
            }
        }
    }

    private static void configCompress(TakePhoto takePhoto, PhotoConfig photoConfig) {
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

    private static void configTakePhotoOption(TakePhoto takePhoto, PhotoConfig photoConfig) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        if (photoConfig.isPickTool()) {
            builder.setWithOwnGallery(true);
        }
        if (photoConfig.isCorrectTool()) {
            builder.setCorrectImage(true);
        }
        takePhoto.setTakePhotoOptions(builder.create());

    }

    private static CropOptions getCropOptions(PhotoConfig photoConfig) {
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
