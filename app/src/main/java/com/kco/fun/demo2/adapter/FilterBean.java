package com.kco.fun.demo2.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 666666 on 2018/5/31.
 */
public class FilterBean {
    private String type;
    private String name;
    private int[] images;

    public static List<FilterBean> getList() {
        List<FilterBean> list = new ArrayList<>();
        list.add(new FilterBean("ptuFacesticker", "大头贴", ImageDrawable.ptu_facesticker));
        list.add(new FilterBean("ptuFacecosmetic", "人脸美妆", ImageDrawable.ptu_facecosmetic));
        list.add(new FilterBean("ptuImgfilter", "人物滤镜", ImageDrawable.ptu_imgfilter));
        list.add(new FilterBean("visionImgfilter", "风景滤镜", ImageDrawable.vision_imgfilter));
        list.add(new FilterBean("ptuFacemerge", "人脸融合", ImageDrawable.ptu_facemerge));
        return list;
    }

    public FilterBean(String type, String name, int[] images) {
        this.type = type;
        this.name = name;
        this.images = images;
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

    public List<Integer> getImages() {
        List<Integer> result = new ArrayList<>();
        for (int image : images){
            result.add(image);
        }
        return result;
    }
}
