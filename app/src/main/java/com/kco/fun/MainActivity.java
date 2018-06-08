package com.kco.fun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import butterknife.ButterKnife;
import butterknife.OnClick;
import com.kco.fun.activity.demo1.SimpleActivity;
import com.kco.fun.activity.demo2.PhotoActivity;
import com.kco.fun.activity.demo3.ImageRecognitionActivity;
import com.kco.fun.activity.demo4.OcrRecognitionActivity;
import com.kco.fun.activity.demo5.AlbumActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_simple)
    public void startSimple(View view){
        Intent intent = new Intent(this, SimpleActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.start_photo)
    public void startPhoto(View view){
        Intent intent = new Intent(this, PhotoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.start_imageRecognition)
    public void startImageRecognition(View view){
        Intent intent = new Intent(this, ImageRecognitionActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.start_ocrRecognition)
    public void startOcrRecognition(View view){
        Intent intent = new Intent(this, OcrRecognitionActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.start_alburm)
    public void start_alburm(View view){
        Intent intent = new Intent(this, AlbumActivity.class);
        startActivity(intent);
    }
}
