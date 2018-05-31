package com.kco.fun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.kco.fun.demo1.SimpleActivity;
import com.kco.fun.demo2.PhotoActivity;

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
}
