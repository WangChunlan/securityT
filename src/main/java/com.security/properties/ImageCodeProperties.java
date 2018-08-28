package com.security.properties;

/**
 * @ClassName ImageCodeProperties
 * @Description  图片验证码属性
 * @Author wangchunlan
 * @Date 2018/8/20 10:52
 * @Version 1.0
 **/
public class ImageCodeProperties  extends  CodeProperties{
    private int width=67;
    private int height=23;


    public ImageCodeProperties() {
        setLength(4);
    }
    public ImageCodeProperties(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ImageCodeProperties(int width, int height, int length, int expireIn, int width1, int height1) {
        super(width, height, length, expireIn);
        this.width = width1;
        this.height = height1;
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


}
