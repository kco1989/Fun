package com.kco.fun.enums;

/**
 * Created by 666666 on 2018/6/4.
 */

public enum ImageRecognitionEnum {
    vision_scener("场景识别"),
    vision_objectr("物体识别"),
    image_tag("图像标签识别"),
    vision_imgidentify_flower("花草识别"),
    vision_imgidentify_car("车辆识别"),
    vision_imgtotext("看图说话");

    private String name;

    ImageRecognitionEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
