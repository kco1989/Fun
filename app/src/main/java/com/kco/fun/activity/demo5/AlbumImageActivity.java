package com.kco.fun.activity.demo5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kco.fun.R;
import com.kco.fun.adapter.AlbumImageListAdapter;
import com.kco.fun.adapter.AlbumListAdapter;
import com.kco.fun.tools.AlbumTools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by 666666 on 2018/6/8.
 */

public class AlbumImageActivity extends AppCompatActivity {
    public static final String ALBURM_INFO = "alburmInfo";
    @BindView(R.id.imageRV)
    RecyclerView imageRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        String alburmInfo = intent.getExtras().getString(ALBURM_INFO);
        LinearLayoutManager filterListLayoutManager = new LinearLayoutManager(this);
        filterListLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        imageRV.setLayoutManager(filterListLayoutManager);
        AlbumImageListAdapter albumImageListAdapter = new AlbumImageListAdapter(this);
        imageRV.setAdapter(albumImageListAdapter);
        AlbumTools.listPicUrl(this, albumImageListAdapter, alburmInfo);
    }

    @OnClick(R.id.nextImageGroup)
    public void click(View view){

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
