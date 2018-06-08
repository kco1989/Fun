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
import com.kco.fun.tools.RxTencentAiTools;
import com.kco.fun.tools.TencentAiTools;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileOutputStream;
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
    public AlbumImageListAdapter(Context context, List<String> picUrls) {
        this.context = context;
        this.picUrls = picUrls;
    }


    @Override
    public ImageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageListViewHolder(LayoutInflater.from(context).inflate(R.layout.alburm_image_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ImageListViewHolder holder, final int position) {
        final String url = picUrls.get(position);
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<File> emitter) throws Exception {
                String[] split = url.split("\\s+");
                String replace = split[1].replace("http://", "");
                File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DCIM);
                File file = new File(externalFilesDir, "image/" + replace);
                if (!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()){
                    IOUtils.write(Jsoup.connect(split[1])
                            .ignoreContentType(true)
                            .header("Referer", split[0])
                            .header("Host", "img1.mm131.me")
                            .header("Pragma", "no-cache")
                            .execute().bodyAsBytes(), new FileOutputStream(file));
                }
                emitter.onNext(file);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(File file) throws Exception {
                        Glide.with(context).load(file).into(holder.imageView);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return picUrls.size();
    }

}
