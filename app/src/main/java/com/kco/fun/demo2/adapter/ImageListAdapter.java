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
import com.kco.fun.tools.PhotoBeautifyEnum;
import com.kco.fun.tools.RxTencentAiTools;
import com.kco.fun.tools.TencentAiTools;
import com.kco.fun.tools.bean.ImageBean;
import com.kco.fun.tools.bean.ResultBean;

import java.io.File;

import io.reactivex.functions.Consumer;

/**
 * Created by 666666 on 2018/5/31.
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListViewHolder> {

    private static final String TAG = "ImageListAdapter";
    private PhotoBeautifyEnum photoBeautifyEnum;
    Context context;
    public File lastFile;

    public ImageListAdapter(Context context) {
        this.context = context;
    }

    public PhotoBeautifyEnum getPhotoBeautifyEnum() {
        return photoBeautifyEnum;
    }

    public void setPhotoBeautifyEnum(PhotoBeautifyEnum photoBeautifyEnum) {
        this.photoBeautifyEnum = photoBeautifyEnum;
        notifyDataSetChanged();
    }


    @Override
    public ImageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageListViewHolder(LayoutInflater.from(context).inflate(R.layout.image_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageListViewHolder holder, final int position) {
        if (photoBeautifyEnum == null) {
            return;
        }
        String image = this.photoBeautifyEnum.getImageUrlList().get(position);
        Glide.with(context).load(image).into(holder.imageView);
        Log.d(TAG, image);
        final PhotoActivity photoActivity = (PhotoActivity) context;
        if (photoActivity.imageFile == null) {
            return;
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fileName = photoBeautifyEnum.name() + "-" + position + "-" + photoActivity.imageFile.getName();
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
                RxTencentAiTools.beautify(photoBeautifyEnum, photoActivity.imageFile, position + 1, new Consumer<ResultBean<ImageBean>>() {
                    @Override
                    public void accept(ResultBean<ImageBean> imageBeanResultBean) throws Exception {
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
        return photoBeautifyEnum == null ? 0 : photoBeautifyEnum.getImageUrlList().size();
    }

}
