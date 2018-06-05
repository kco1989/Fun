package com.kco.fun.bean;

/**
 * Created by 666666 on 2018/5/31.
 */
public class PhotoConfig {
    // 是否裁切
    private boolean isCrop = false;
    // true 第三方 ; false TakePhoto自带
    private boolean cropTool = false;
    // true 宽x高; false 宽/高
    private boolean cropSize = false;
    private int cropWidth = 800;
    private int cropHeight = 800;
    //true 自带; false Luban
    private boolean compressTool = false;
    private boolean isCompress = true;
    private boolean isShowProgressBar = true;
    private int compressSize = 102400;
    private int compressWidth = 800;
    private int compressHeight = 800;
    // 使用TakePhoto自带相册 true 是, false 侯
    private boolean pickTool = true;
    private int limit = 1;
    // true 从相册; false 从文件
    private boolean from = true;
    // 纠正拍照的照片旋转角度
    private boolean isCorrectTool = false;
    // 拍照压缩后是否保存原图
    private boolean isRawFile = true;

    public boolean isCrop() {
        return isCrop;
    }

    public void setCrop(boolean crop) {
        isCrop = crop;
    }

    public boolean isCropTool() {
        return cropTool;
    }

    public void setCropTool(boolean cropTool) {
        this.cropTool = cropTool;
    }

    public boolean isCropSize() {
        return cropSize;
    }

    public void setCropSize(boolean cropSize) {
        this.cropSize = cropSize;
    }

    public int getCropWidth() {
        return cropWidth;
    }

    public void setCropWidth(int cropWidth) {
        this.cropWidth = cropWidth;
    }

    public int getCropHeight() {
        return cropHeight;
    }

    public void setCropHeight(int cropHeight) {
        this.cropHeight = cropHeight;
    }

    public boolean isCompressTool() {
        return compressTool;
    }

    public void setCompressTool(boolean compressTool) {
        this.compressTool = compressTool;
    }

    public boolean isCompress() {
        return isCompress;
    }

    public void setCompress(boolean compress) {
        isCompress = compress;
    }

    public boolean isShowProgressBar() {
        return isShowProgressBar;
    }

    public void setShowProgressBar(boolean showProgressBar) {
        isShowProgressBar = showProgressBar;
    }

    public int getCompressSize() {
        return compressSize;
    }

    public void setCompressSize(int compressSize) {
        this.compressSize = compressSize;
    }

    public int getCompressWidth() {
        return compressWidth;
    }

    public void setCompressWidth(int compressWidth) {
        this.compressWidth = compressWidth;
    }

    public int getCompressHeight() {
        return compressHeight;
    }

    public void setCompressHeight(int compressHeight) {
        this.compressHeight = compressHeight;
    }

    public boolean isPickTool() {
        return pickTool;
    }

    public void setPickTool(boolean pickTool) {
        this.pickTool = pickTool;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isFrom() {
        return from;
    }

    public void setFrom(boolean from) {
        this.from = from;
    }

    public boolean isCorrectTool() {
        return isCorrectTool;
    }

    public void setCorrectTool(boolean correctTool) {
        isCorrectTool = correctTool;
    }

    public boolean isRawFile() {
        return isRawFile;
    }

    public void setRawFile(boolean rawFile) {
        isRawFile = rawFile;
    }
}
