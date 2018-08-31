package com.security.social.qq.connet;

import com.security.social.qq.api.QQ;
import com.security.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * 服务提供商 provider
 *  1、认证服务器 Authorization Server
 *  2、资源服务器 Resource Server
 *      用户信息
 * @ClassName QQServiceProvider
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/27 17:54
 * @Version 1.0
 **/
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;
    // 认证
    // 获取Authorization Code 【3】
    private static final String URL_AUTHORIZE ="https://graph.qq.com/oauth2.0/authorize";
    // 获取ACCESS_TOKEN
    // 通过Authorization Code获取Access Token 【4】
    private static final String URL_ACCESS_TOKEN ="https://graph.qq.com/oauth2.0/token";


    /**
     *
     * URL_AUTHORIZE: 将用户导向认证服务器上  【1】  client-->service 认证服务器 Authorization Service
     * URL_ACCESS_TOKEN： 申请令牌 【4】         client-->service
     * @param appId
     * @param appSecret
     */
    public QQServiceProvider(String   appId, String appSecret) {
        super(new QQOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }


    /**
     * 资源服务器：
     *  获取用户基本信息
     * @param accessToken
     * @return
     */

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
