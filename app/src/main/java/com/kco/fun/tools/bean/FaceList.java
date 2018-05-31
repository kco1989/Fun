package com.kco.fun.tools.bean;

/**
 * Created by 666666 on 2018/5/30.
 */
public class FaceList {
    private String face_id;
    private int x;
    private int y;
    private int width;
    private int height;
    private int gender;
    private int age;
    private int expression;
    private int beauty;
    private int glass;
    private int pitch;
    private long yaw;
    private int roll;
    private faceShape face_shape;

    public String getFace_id() {
        return face_id;
    }

    public void setFace_id(String face_id) {
        this.face_id = face_id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExpression() {
        return expression;
    }

    public void setExpression(int expression) {
        this.expression = expression;
    }

    public int getBeauty() {
        return beauty;
    }

    public void setBeauty(int beauty) {
        this.beauty = beauty;
    }

    public int getGlass() {
        return glass;
    }

    public void setGlass(int glass) {
        this.glass = glass;
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public long getYaw() {
        return yaw;
    }

    public void setYaw(long yaw) {
        this.yaw = yaw;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public faceShape getFace_shape() {
        return face_shape;
    }

    public void setFace_shape(faceShape face_shape) {
        this.face_shape = face_shape;
    }
}
