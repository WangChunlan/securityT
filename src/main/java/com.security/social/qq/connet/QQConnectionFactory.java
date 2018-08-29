package com.security.social.qq.connet;

import com.security.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @ClassName QQConnectionFactory
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/28 9:27
 * @Version 1.0
 **/
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    public QQConnectionFactory(String providerId,String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
