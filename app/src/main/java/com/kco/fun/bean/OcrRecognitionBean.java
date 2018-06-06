package com.kco.fun.bean;

import java.util.List;
import java.util.Map;

/**
 * 身份证OCR识别
 * + name	是	string	证件姓名
 * + sex	是	string	性别
 * + nation	是	string	民族
 * + birth	是	string	出生日期
 * + address	是	string	地址
 * + id	是	string	身份证号
 * + frontimage	是	string	OCR识别的身份证正面base64编码照片
 * + valid_date	是	string	证件的有效期
 * + authority	是	string	发证机关
 * + backimage	是	string	OCR识别的证件身份证反面base64编码照片
 *
 * 名片OCR识别
 * + angle	是	string	请求图片旋转角度
 * + item_list	是	array	识别出的所有字段信息
 * + + item	是	string	字段名称
 * + + itemstring	是	string	字段识别出来的信息
 * + + itemcoord	是	object	字段在图像中的像素坐标，包括左上角坐标x,y，以及宽、高width, height
 * + + words	是	array	字段识别出来的每个字的信息
 * + + + character	是	string	识别出的单字字符
 * + + + confidence	是	float	识别出的单字字符对应的置信度
 *
 * 行驶证驾驶证OCR识别
 * + item_list	是	array	识别出的所有字段信息
 * + + item	是	string	字段名称
 * + + itemstring	是	string	字段识别出来的信息
 * + + itemcoord	是	object	字段在图像中的像素坐标，包括左上角坐标x,y，以及宽、高width, height
 * + + itemconf	是	float	字段置信度
 *
 * 营业执照OCR识别
 * + item_list	是	array	识别出的所有字段信息
 * + + item	是	string	字段名称
 * + + itemstring	是	string	字段识别出来的信息
 * + + itemcoord	是	object	字段在图像中的像素坐标，包括左上角坐标x,y，以及宽、高width, height
 * + + itemconf	是	float	字段置信度
 *
 * 银行卡OCR识别
 * + item_list	是	array	识别出的所有字段信息
 * + + item	是	string	字段名称
 * + + itemstring	是	string	字段识别出来的信息
 * + + itemcoord	是	object	字段在图像中的像素坐标，包括左上角坐标x,y，以及宽、高width, height
 * + + itemconf	是	float	字段置信度
 *
 * 通用OCR识别
 * + item_list	是	array	识别出的所有字段信息
 * + + item	是	string	字段名称
 * + + itemstring	是	string	字段识别出来的信息
 * + + itemcoord	是	object	字段在图像中的像素坐标，包括左上角坐标x,y，以及宽、高width, height
 * + + words	是	array	字段识别出来的每个字的信息
 * + + + character	是	string	识别出的单字字符
 * + + + confidence	是	float	识别出的单字字符对应的置信度
 */

public class OcrRecognitionBean {
    private String text;
    private String name;
    private String sex;
    private String nation;
    private String birth;
    private String address;
    private String id;
    private String frontimage;
    private String valid_date;
    private String authority;
    private String backimage;

    private String angle;
    private List<Item> item_list;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrontimage() {
        return frontimage;
    }

    public void setFrontimage(String frontimage) {
        this.frontimage = frontimage;
    }

    public String getValid_date() {
        return valid_date;
    }

    public void setValid_date(String valid_date) {
        this.valid_date = valid_date;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getBackimage() {
        return backimage;
    }

    public void setBackimage(String backimage) {
        this.backimage = backimage;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public List<Item> getItem_list() {
        return item_list;
    }

    public void setItem_list(List<Item> item_list) {
        this.item_list = item_list;
    }

    public static class Item {
        private String item;
        private String itemstring;
//        private Map<String, Object> itemcoord;
        private float itemconf;
//        private Map<String, Object> words;  // character, confidence

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getItemstring() {
            return itemstring;
        }

        public void setItemstring(String itemstring) {
            this.itemstring = itemstring;
        }


        public float getItemconf() {
            return itemconf;
        }

        public void setItemconf(float itemconf) {
            this.itemconf = itemconf;
        }

    }


}
