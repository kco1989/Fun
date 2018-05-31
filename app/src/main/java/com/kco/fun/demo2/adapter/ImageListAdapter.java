package com.kco.fun.demo2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.kco.fun.R;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by 666666 on 2018/5/31.
 */
public class ImageListAdapter extends ArrayAdapter<Integer> {

    public ImageListAdapter(Context context, List<Integer> images) {
        super(context, R.layout.image_list, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int image = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.image_list, null);
        ImageView imageView = view.findViewById(R.id.image_item);
        Glide.with(getContext()).load(image).into(imageView);
        return view;
    }

}
