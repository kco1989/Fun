package com.kco.fun.tools.bean;

import java.util.List;

/**
 * Created by 666666 on 2018/5/30.
 */
public class DetectFaceBean {
    private int image_width;
    private int image_height;
    private List<FaceList> face_list;

    public int getImage_width() {
        return image_width;
    }

    public void setImage_width(int image_width) {
        this.image_width = image_width;
    }

    public int getImage_height() {
        return image_height;
    }

    public void setImage_height(int image_height) {
        this.image_height = image_height;
    }

    public List<FaceList> getFace_list() {
        return face_list;
    }

    public void setFace_list(List<FaceList> face_list) {
        this.face_list = face_list;
    }
}
