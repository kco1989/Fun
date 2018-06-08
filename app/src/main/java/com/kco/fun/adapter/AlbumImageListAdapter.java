package com.kco.fun.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kco.fun.R;
import com.kco.fun.activity.demo2.PhotoActivity;
import com.kco.fun.bean.ImageBean;
import com.kco.fun.bean.ResultBean;
import com.kco.fun.enums.PhotoBeautifyEnum;
import com.kco.fun.tools.AlbumTools;
import com.kco.fun.tools.RxTencentAiTools;
import com.kco.fun.tools.TencentAiTools;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 666666 on 2018/5/31.
 */
public class AlbumImageListAdapter extends RecyclerView.Adapter<ImageListViewHolder> {

    private static final String TAG = "ImageListAdapter";
    private List<String> picUrls;
    Context context;
    public AlbumImageListAdapter(Context context) {
        this.context = context;
        this.picUrls = new ArrayList<>();
    }

    public void addPiceUrl(String url){
        this.picUrls.add(url);
        notifyDataSetChanged();
    }


    @Override
    public ImageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageListViewHolder(LayoutInflater.from(context).inflate(R.layout.alburm_image_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ImageListViewHolder holder, final int position) {
        final String url = picUrls.get(position);
        String[] split = url.split("\\s+");
        AlbumTools.showImage(context, Environment.DIRECTORY_DCIM, split[1], split[0], holder.imageView);
    }

    @Override
    public int getItemCount() {
        return picUrls.size();
    }

}
