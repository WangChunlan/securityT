package com.security.social.qq.connet;

import com.security.social.qq.api.QQ;
import com.security.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @ClassName QQServiceProvider
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/27 17:54
 * @Version 1.0
 **/
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;
    private static final String URL_AUTHORIZE ="https://graph.qq.com/oauth2.0/authorize";
    private static final String URL_ACCESS_TOKEN ="https://graph.qq.com/oauth2.0/token";


    public QQServiceProvider(String   appId, String appSecret) {
        super(new OAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
