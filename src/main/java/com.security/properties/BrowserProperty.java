package com.security.properties;

/**
 * @ClassName BrowserProperty
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/27 16:38
 * @Version 1.0
 **/
public class BrowserProperty {
    private int rememberMeSeconds = 3600;

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
