package com.kco.fun.demo2.adapter;

import com.kco.fun.tools.PhotoBeautifyEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 666666 on 2018/5/31.
 */
public class FilterBean {
    private PhotoBeautifyEnum photoBeautifyEnum;

    public static List<FilterBean> getList() {
        List<FilterBean> list = new ArrayList<>();
        for (PhotoBeautifyEnum photoBeautifyEnum : PhotoBeautifyEnum.values()){
            list.add(new FilterBean(photoBeautifyEnum));
        }
        return list;
    }

    public FilterBean(PhotoBeautifyEnum photoBeautifyEnum) {
        this.photoBeautifyEnum = photoBeautifyEnum;
    }

    public PhotoBeautifyEnum getPhotoBeautifyEnum() {
        return photoBeautifyEnum;
    }
}
