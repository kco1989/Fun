package com.kco.fun.demo2.adapter;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kco.fun.R;
import com.kco.fun.demo2.PhotoActivity;
import com.kco.fun.tools.TencentAiTools;
import com.kco.fun.tools.bean.ImageBean;
import com.kco.fun.tools.bean.ResultBean;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by 666666 on 2018/5/31.
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListViewHolder> {

    private static final String TAG = "ImageListAdapter";
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    public List<String> imageList;
    public String type;
    Context context;
    public File lastFile;

    public ImageListAdapter(Context context) {
        this.context = context;
    }

    public void setImageList(List<String> imageList, String type) {
        this.imageList = imageList;
        this.type = type;
        notifyDataSetChanged();
    }

    public List<String> getImageList() {
        return imageList;
    }

    @Override
    public ImageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageListViewHolder(LayoutInflater.from(context).inflate(R.layout.image_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageListViewHolder holder, final int position) {
        if (imageList == null) {
            return;
        }
        String image = imageList.get(position);
        Glide.with(context).load(image).into(holder.imageView);
        Log.d(TAG, image);
        final PhotoActivity photoActivity = (PhotoActivity) context;
        if (photoActivity.imageFile == null) {
            return;
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fileName = type + "-" + position + "-" + photoActivity.imageFile.getName();
                File externalFilesDir = photoActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                if (!externalFilesDir.exists()) {
                    externalFilesDir.mkdirs();
                }
                final File file = new File(externalFilesDir, fileName);
                if (file.exists()) {
                    Glide.with(photoActivity).load(file).into(photoActivity.imageView);
                    lastFile = file;
                    return;
                }
                Observable.create(new Observable.OnSubscribe<ResultBean<ImageBean>>() {
                    @Override
                    public void call(Subscriber<? super ResultBean<ImageBean>> subscriber) {
                        ResultBean<ImageBean> imageBeanResultBean = null;
                        switch (type) {
                            case "ptuFacesticker":
                                imageBeanResultBean = TencentAiTools.ptuFacesticker(photoActivity.imageFile, position + 1);
                                break;
                            case "ptuFacecosmetic":
                                imageBeanResultBean = TencentAiTools.ptuFacecosmetic(photoActivity.imageFile, position + 1);
                                break;
                            case "ptuImgfilter":
                                imageBeanResultBean = TencentAiTools.ptuImgfilter(photoActivity.imageFile, position + 1);
                                break;
                            case "visionImgfilter":
                                imageBeanResultBean = TencentAiTools.visionImgfilter(photoActivity.imageFile, position + 1);
                                break;
                            case "ptuFacemerge":
                                imageBeanResultBean = TencentAiTools.ptuFacemerge(photoActivity.imageFile, position + 1);
                                break;
                            default:
                                break;
                        }
                        subscriber.onNext(imageBeanResultBean);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResultBean<ImageBean>>() {
                    @Override
                    public void call(ResultBean<ImageBean> imageBeanResultBean) {
                        if (imageBeanResultBean == null) {
                            return;
                        }
                        if (imageBeanResultBean.getRet() != 0) {
                            Toast.makeText(photoActivity, imageBeanResultBean.getRet() + "" + imageBeanResultBean.getMsg(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        TencentAiTools.Base64ToFile(imageBeanResultBean.getData().getImage(), file);
                        Glide.with(photoActivity).load(file).into(photoActivity.imageView);
                        lastFile = file;
                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList == null ? 0 : imageList.size();
    }

}
