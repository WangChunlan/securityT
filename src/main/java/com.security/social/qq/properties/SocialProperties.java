package com.security.social.qq.properties;

/**
 * @ClassName SocialProperties
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/28 13:32
 * @Version 1.0
 **/
public class SocialProperties {
    private  QQProperties qq=new QQProperties();

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
