package com.security.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.http.util.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @ClassName QQImpl
 * @Description TODO
 *      多例的 不然单例 容易线程不安全
 * @Author wangchunlan
 * @Date 2018/8/27 17:19
 * @Version 1.0
 **/
public class QQImpl  extends AbstractOAuth2ApiBinding implements QQ{

    private static final  String URL_GET_OPENID="https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final  String URL_GET_USERINFO="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;
    private String openId;

//    @Autowired
    private ObjectMapper objectMapper=new ObjectMapper();

    public QQImpl(String accessToken,String appId) {
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url=String.format(URL_GET_OPENID,accessToken);
        String result=getRestTemplate().getForObject(url,String.class);
        System.out.print(result);
        this.openId=StringUtils.substringBetween(result,"\"openid\":","}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url=String.format(URL_GET_USERINFO,appId,openId);
        String result=getRestTemplate().getForObject(url,String.class);
        System.out.print(result);

        try {
            return objectMapper.readValue(result,QQUserInfo.class);
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败");
        }
    }
}
