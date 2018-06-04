package com.kco.fun.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.sip.SipAudioCall;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by 666666 on 2018/6/4.
 */

public class PictureUtils {
    private static final String TAG = "PictureUtils";
    public static void compress(Context context,
                                File srcFile, OnCompressListener listener) {
        if (!srcFile.exists()){
            return;
        }
        Luban.with(context).load(srcFile)
                .setTargetDir(context.getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath())
                .ignoreBy(500 * 1024)
                .setCompressListener(listener)
                .launch();
    }


}
