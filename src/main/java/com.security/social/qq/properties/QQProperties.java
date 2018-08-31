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
public class QQProperties  extends SocialProperties {
    private String providerId="qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
