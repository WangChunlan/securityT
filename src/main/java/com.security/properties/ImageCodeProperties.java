package com.security.properties;

/**
 * @ClassName ImageCodeProperties
 * @Description  图片验证码属性
 * @Author wangchunlan
 * @Date 2018/8/20 10:52
 * @Version 1.0
 **/
public class ImageCodeProperties  extends SmsCodeProperties {
    private int width=67;
    private int height=23;


    public ImageCodeProperties() {
        setLength(4);
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
