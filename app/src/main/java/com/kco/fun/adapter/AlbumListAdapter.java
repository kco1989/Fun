package com.kco.fun.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kco.fun.R;
import com.kco.fun.activity.demo5.AlbumImageActivity;
import com.kco.fun.tools.AlbumTools;

import org.apache.commons.io.IOUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by 666666 on 2018/5/31.
 */
public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListViewHolder> {
    private static final String TAG = "AlbumListAdapter";
    private Context context;
    private List<String> alburmInfos;
    public AlbumListAdapter(Context context) {
        this.context = context;
        try {
            alburmInfos = IOUtils.readLines(context.getAssets().open("image.txt"), "utf8");
            Collections.shuffle(alburmInfos);
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
        try{
            Log.d(TAG, "onBindViewHolder");
            final String alburmInfo = alburmInfos.get(position);
            final String[] split = alburmInfo.split("\t");
            holder.albumTV.setText(split[1]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AlbumImageActivity.class);
                    intent.putExtra(AlbumImageActivity.ALBURM_INFO, alburmInfo);
                    context.startActivity(intent);
                }
            });
            AlbumTools.showImage(context, Environment.DIRECTORY_PICTURES, split[2], split[0], holder.albumIV);
        }catch (Exception e){
            Log.e(TAG, "异常信息: " + e);
        }

    }

    @Override
    public int getItemCount() {
        return alburmInfos.size();
    }

}
