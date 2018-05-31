package com.kco.fun.tools.bean;

import java.util.List;

/**
 * Created by 666666 on 2018/5/30.
 */
public class faceShape {
    private List<Point> face_profile;
    private List<Point> left_eye;
    private List<Point> right_eye;
    private List<Point> left_eyebrow;
    private List<Point> right_eyebrow;
    private List<Point> mouth;
    private List<Point> nose;

    public List<Point> getFace_profile() {
        return face_profile;
    }

    public void setFace_profile(List<Point> face_profile) {
        this.face_profile = face_profile;
    }

    public List<Point> getLeft_eye() {
        return left_eye;
    }

    public void setLeft_eye(List<Point> left_eye) {
        this.left_eye = left_eye;
    }

    public List<Point> getRight_eye() {
        return right_eye;
    }

    public void setRight_eye(List<Point> right_eye) {
        this.right_eye = right_eye;
    }

    public List<Point> getLeft_eyebrow() {
        return left_eyebrow;
    }

    public void setLeft_eyebrow(List<Point> left_eyebrow) {
        this.left_eyebrow = left_eyebrow;
    }

    public List<Point> getRight_eyebrow() {
        return right_eyebrow;
    }

    public void setRight_eyebrow(List<Point> right_eyebrow) {
        this.right_eyebrow = right_eyebrow;
    }

    public List<Point> getMouth() {
        return mouth;
    }

    public void setMouth(List<Point> mouth) {
        this.mouth = mouth;
    }

    public List<Point> getNose() {
        return nose;
    }

    public void setNose(List<Point> nose) {
        this.nose = nose;
    }
}
