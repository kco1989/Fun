package com.kco.fun.activity.demo5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kco.fun.R;
import com.kco.fun.adapter.AlbumListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 666666 on 2018/6/8.
 */

public class AlbumActivity extends AppCompatActivity {
    public static final String IMAGE_TYPE = "IMAGE_TYPE";
    @BindView(R.id.albumRV)
    RecyclerView albumRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String imageType = intent.getExtras().getString(IMAGE_TYPE);
        LinearLayoutManager filterListLayoutManager = new LinearLayoutManager(this);
        filterListLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        albumRV.setLayoutManager(filterListLayoutManager);
        albumRV.setAdapter(new AlbumListAdapter(this, imageType));
    }

    public class IMAGE_TYPE {
    }
}
