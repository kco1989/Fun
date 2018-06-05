package com.kco.fun.activity.demo2;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.zibin.luban.OnCompressListener;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.*;
import com.kco.fun.R;
import com.kco.fun.bean.PhotoConfig;
import com.kco.fun.adapter.FilterListAdapter;
import com.kco.fun.adapter.ImageListAdapter;
import com.kco.fun.tools.PictureUtils;
import com.kco.fun.tools.TakePhotoUtils;

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

    public File imageFile;
    ImageListAdapter imageListAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phtoto_layout);
        ButterKnife.bind(this);

        imageListAdapter = new ImageListAdapter(this);
        FilterListAdapter filterListAdapter = new FilterListAdapter(this, imageListAdapter);

        LinearLayoutManager filterListLayoutManager = new LinearLayoutManager(this);
        filterListLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        filterList.setLayoutManager(filterListLayoutManager);
        filterList.setAdapter(filterListAdapter);

        LinearLayoutManager imageListLayoutManager = new LinearLayoutManager(this);
        imageListLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        imageList.setLayoutManager(imageListLayoutManager);
        imageList.setAdapter(imageListAdapter);

        filterList.setVisibility(View.INVISIBLE);
        imageList.setVisibility(View.INVISIBLE);
        Log.d(TAG, "onCreate");
    }

    @OnClick(R.id.btnSumbit)
    public void sumbit(View view) {
        PictureUtils.compress(this, 500, imageListAdapter.lastFile, new OnCompressListener() {
            @Override
            public void onStart() {}

            @Override
            public void onSuccess(File file) {
                imageFile = file;
                Glide.with(PhotoActivity.this).load(file).into(imageView);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        filterList.setVisibility(View.VISIBLE);
        imageList.setVisibility(View.VISIBLE);
        Log.d(TAG, result.getImage().getOriginalPath());
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
        PictureUtils.compress(this, 500, new File(image.getOriginalPath()), new OnCompressListener() {
            @Override
            public void onStart() {
                System.out.println("onStart");
            }

            @Override
            public void onSuccess(File file) {
                imageFile = file;
                Glide.with(PhotoActivity.this).load(file).into(imageView);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }
        });

    }

    @OnClick({R.id.btnPickByTake, R.id.btnPickBySelect})
    public void pick(View view){
        switch (view.getId()) {
            case R.id.btnPickBySelect:
                TakePhotoUtils.pickBySelect(this, photoConfig, true);
                break;
            case R.id.btnPickByTake:
                TakePhotoUtils.pickBySelect(this, photoConfig, false);
                break;
            default:
                break;
        }
    }
}
