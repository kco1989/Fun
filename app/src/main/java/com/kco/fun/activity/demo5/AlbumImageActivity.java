package com.kco.fun.activity.demo5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kco.fun.R;
import com.kco.fun.adapter.AlbumImageListAdapter;
import com.kco.fun.adapter.AlbumListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 666666 on 2018/6/8.
 */

public class AlbumImageActivity extends AppCompatActivity {
    @BindView(R.id.imageRV)
    RecyclerView imageRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        List<String> picUrls = new Gson().fromJson(intent.getExtras().get("picUrls").toString(), new TypeToken<List<String>>() {
        }.getType());
        LinearLayoutManager filterListLayoutManager = new LinearLayoutManager(this);
        filterListLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        imageRV.setLayoutManager(filterListLayoutManager);
        imageRV.setAdapter(new AlbumImageListAdapter(this, picUrls));
    }
}
