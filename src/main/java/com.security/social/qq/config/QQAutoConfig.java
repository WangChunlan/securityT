package com.security.social.qq.config;

import com.security.properties.MySecurityProperties;

import com.security.social.qq.connet.QQConnectionFactory;
import com.security.social.qq.properties.QQProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @ClassName QQAutoConfig
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/28 13:37
 * @Version 1.0
 **/

@Configuration
@ConditionalOnProperty(prefix = "com.security.social.qq",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
//    private SecurityCodeConfigure securityProperties;
    private MySecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties=securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProviderId(),qqProperties.getAppId(),qqProperties.getAppSecret());
    }
}
