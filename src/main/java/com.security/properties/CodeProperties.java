package com.security.properties;

/**
 * @ClassName CodeProperties
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/22 10:03
 * @Version 1.0
 **/
public class CodeProperties {

    private int length=6;
    private int expireIn=360;
    private String url;


    public CodeProperties() {
    }

    public CodeProperties(int width, int height, int length, int expireIn) {
        this.length = length;
        this.expireIn = expireIn;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
