package com.kco.fun.tools;

import com.kco.fun.demo2.adapter.ImageDrawable;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片融合
 * Created by 666666 on 2018/6/4.
 */
public enum PhotoBeautifyEnum {
    ptuFacecosmetic("人脸美妆", ImageDrawable.ptu_facecosmetic),
    ptuImgfilter("人物滤镜", ImageDrawable.ptu_imgfilter),
    visionImgfilter("风景滤镜", ImageDrawable.vision_imgfilter),
    ptuFacesticker("大头贴", ImageDrawable.ptu_facesticker),
    ptuFacemerge("人脸融合", ImageDrawable.ptu_facemerge);

    private String describe;
    private int maxSize;
    private String[] imageUrls;
    private List<String> imageUrlList = new ArrayList<>();

    PhotoBeautifyEnum(String describe, String[] imageUrls) {
        this.describe = describe;
        this.imageUrls = imageUrls;
        this.maxSize = 0;
        if (this.imageUrls != null){
            this.maxSize = this.imageUrls.length;
            for (String url : this.imageUrls){
                imageUrlList.add(url);
            }
        }
    }

    public String getDescribe() {
        return describe;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public String[] getImageUrls() {
        return imageUrls;
    }

    public List<String> getImageUrlList() {
        return imageUrlList;
    }
}
