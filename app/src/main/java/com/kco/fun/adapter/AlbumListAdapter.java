package com.kco.fun.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kco.fun.R;
import com.kco.fun.activity.demo5.AlbumImageActivity;
import com.kco.fun.tools.AlbumTools;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

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
public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListViewHolder> {
    private static final String TAG = "FilterListAdapter";
    private Context context;
    private List<String> alburmInfos;
    public AlbumListAdapter(Context context) {
        this.context = context;
        try {
            alburmInfos = IOUtils.readLines(context.getAssets().open("image.txt"), "utf8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "FilterListAdapter");
    }

    @Override
    public AlbumListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        return new AlbumListViewHolder(LayoutInflater.from(context).inflate(R.layout.album_list, parent,false));
    }

    @Override
    public void onBindViewHolder(final AlbumListViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        final String alburmInfo = alburmInfos.get(position);
        final String[] split = alburmInfo.split("\\s+");
        holder.albumTV.setText(split[1]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlbumTools.listOnClick(context, alburmInfo);
            }
        });
        AlbumTools.showImage(context, Environment.DIRECTORY_PICTURES, split[2], split[0], holder.albumIV);
    }

    @Override
    public int getItemCount() {
        return alburmInfos.size();
    }

}
