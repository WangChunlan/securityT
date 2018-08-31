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

    /**
     * 创建 ConnectionFactory 实例：
     *  ServiceProvider 和ApiAdapter
     *
     * 1、ServiceProvider实例 供 API：AbstractOAuth2ApiBinding使用
     * 2. QQAdapter   在Api:AbstractOAuth2ApiBinding 和Connection ：OAuth2Connection做一个适配
     * @param providerId 运营商提供
     * @param appId
     * @param appSecret
     */
    public QQConnectionFactory(String providerId,String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
