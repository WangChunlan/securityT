package com.security.properties;

/**
 * @ClassName ValidateCodeProperties
 * @Description 验证码 属性配置
 *              考虑后期不只是 图片验证码 还有短信验证码等等
 * @Author wangchunlan
 * @Date 2018/8/20 10:56
 * @Version 1.0
 **/
public class ValidateCodeProperties {

    private ImageCodeProperties image=new ImageCodeProperties();
    private SmsCodeProperties sms=new SmsCodeProperties();




    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }
}
