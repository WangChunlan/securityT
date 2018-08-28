package com.security.properties;

/**
 * @ClassName ImageCodeProperties
 * @Description  图片验证码属性
 * @Author wangchunlan
 * @Date 2018/8/20 10:52
 * @Version 1.0
 **/
public class SmsCodeProperties extends  CodeProperties{


    public SmsCodeProperties() {
    }

    public SmsCodeProperties(int width, int height, int length, int expireIn) {
        super(width, height, length, expireIn);
    }
}
