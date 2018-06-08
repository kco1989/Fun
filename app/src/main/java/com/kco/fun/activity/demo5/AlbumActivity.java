package com.kco.fun.activity.demo5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kco.fun.R;
import com.kco.fun.adapter.AlbumListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 666666 on 2018/6/8.
 */

public class AlbumActivity extends AppCompatActivity {
    public static final String IMAGE_TYPE = "IMAGE_TYPE";
    @BindView(R.id.albumRV)
    RecyclerView albumRV;

    AlbumListAdapter albumListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ButterKnife.bind(this);

        LinearLayoutManager filterListLayoutManager = new LinearLayoutManager(this);
        filterListLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        albumRV.setLayoutManager(filterListLayoutManager);
        this.albumListAdapter = new AlbumListAdapter(this);
        albumRV.setAdapter(this.albumListAdapter);
    }

    @OnClick({R.id.albumAllBtn, R.id.albumChemoBtn, R.id.albumMingxingBtn,
        R.id.albumQiPaoBtn, R.id.albumQingchunBtn, R.id.albumXiaohuaBtn})
    public void onBtnClick(View view){
        int index = Demo5DataContants.alburm_all_index;
        switch (view.getId()){
            case R.id.albumAllBtn:
                index = Demo5DataContants.alburm_all_index;
                break;
            case R.id.albumChemoBtn:
                index = Demo5DataContants.alburm_chemo_index;
                break;
            case R.id.albumMingxingBtn:
                index = Demo5DataContants.alburm_mingxing_index;
                break;
            case R.id.albumQingchunBtn:
                index = Demo5DataContants.alburm_qingchu_index;
                break;
            case R.id.albumQiPaoBtn:
                index = Demo5DataContants.alburm_qipao_index;
                break;
            case R.id.albumXiaohuaBtn:
                index = Demo5DataContants.alburm_mingxing_index;
                break;
        }
        this.albumListAdapter.setAlburmInfos(index);
        Demo5DataContants.MoveToPosition((LinearLayoutManager) albumRV.getLayoutManager(), albumRV, 0);
    }
}
