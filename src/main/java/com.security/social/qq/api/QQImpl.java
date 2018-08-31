package com.security.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @ClassName QQImpl
 * @Description TODO
 *      多例的 不然单例 容易线程不安全
 *      每一个用户登录上来的，信息是不同的，所以不能将该类注入到IOC容器中
 * @Author wangchunlan
 * @Date 2018/8/27 17:19
 * @Version 1.0
 **/
public class QQImpl  extends AbstractOAuth2ApiBinding implements QQ{

    /**
     * Access Token 父类已经替我们实例化出来
     *  获取到Access Token和OpenID后，可通过调用OpenAPI来获取或修改用户个人信息。
     *
     */
    // 获取openid
    private static final  String URL_GET_OPENID="https://graph.qq.com/oauth2.0/me?access_token=%s";
    // 调用接口，获取用户信息
    private static final  String URL_GET_USERINFO="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;
    private String openId;

    protected Logger logger=LoggerFactory.getLogger(getClass());

//    @Autowired
    private ObjectMapper objectMapper=new ObjectMapper();

    public QQImpl(String accessToken,String appId) {
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url=String.format(URL_GET_OPENID,accessToken);
        String result=getRestTemplate().getForObject(url,String.class);
        logger.info(result);
        this.openId=StringUtils.substringBetween(result,"\"openid\":\"","\"}");
    }

    /**
     * 调用个人信息
     * access_token :在父类中已经初始化
     * oauth_consumer_key
     * openid   ：构造方法中已经实例化出 openId
     *
     * @return
     */
    @Override
    public QQUserInfo getUserInfo() {
        String url=String.format(URL_GET_USERINFO,appId,openId);
        String result=getRestTemplate().getForObject(url,String.class);
        logger.info(result);
        QQUserInfo userInfo=null;
        try {
            userInfo=objectMapper.readValue(result,QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败",e);
        }
    }
}
