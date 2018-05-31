package com.kco.fun.demo2.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 666666 on 2018/5/31.
 */
public class FilterBean {
    private String type;
    private String name;

    public static List<FilterBean> getList() {
        List<FilterBean> list = new ArrayList<>();
        list.add(new FilterBean("ptuFacesticker", "大头贴"));
        list.add(new FilterBean("ptuFacecosmetic", "人脸美妆"));
        list.add(new FilterBean("ptuImgfilter", "人物滤镜"));
        list.add(new FilterBean("visionImgfilter", "风景滤镜"));
        list.add(new FilterBean("ptuFacemerge", "人脸融合"));
        return list;
    }

    public FilterBean(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
