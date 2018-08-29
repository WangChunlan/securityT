package com.security.social.qq.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @ClassName QQProperties
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/28 13:06
 * @Version 1.0
 **/
//  无法导入org.springframework.boot.autoconfigure.social.SocialProperties

/**
 * TODO : 无法导入org.springframework.boot.autoconfigure.social.SocialProperties
 *          原因： springboot 1.4.0+   ~~~2.0.0--  之间才有这个包
 *          解决：更改springBoot版本 从2.0.4 改为 1.5.4
 */
public class QQProperties  extends SocialProperties {
    private String providerId="qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
