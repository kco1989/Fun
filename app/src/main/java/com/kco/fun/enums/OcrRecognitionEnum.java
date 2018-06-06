package com.kco.fun.enums;

/**
 * Created by 666666 on 2018/6/5.
 */

public enum OcrRecognitionEnum {
    ocr_idcardocr_Positive("身份证OCR识别(正)"),
    ocr_idcardocr_opposite("身份证OCR识别(反)"),
    ocr_bcocr("名片OCR识别"),
    ocr_driver("驾驶证OCR识别"),
    ocr_license("行驶证OCR识别"),
    ocr_bizlicenseocr("营业执照OCR识别"),
    ocr_creditcardocr("银行卡OCR识别"),
    ocr_generalocr("通用OCR识别");

    private String name;

    OcrRecognitionEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
