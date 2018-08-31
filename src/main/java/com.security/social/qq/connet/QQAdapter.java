package com.security.social.qq.connet;

import com.security.social.qq.api.QQ;
import com.security.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

/**
 * QQAdapter   在Api:AbstractOAuth2ApiBinding 和Connection ：OAuth2Connection做一个适配
 * @ClassName QQAdapter
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/28 9:14
 * @Version 1.0
 **/
public class QQAdapter implements ApiAdapter<QQ> {
    /**
     * 测试当前API是否可用
     * @param api
     * @return
     */
    @Override
    public boolean test(QQ api) {
        return true;
    }

    /**
     * 在Connetcion和API之间做一个适配
     * @param api
     * @param values
     */
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {

            QQUserInfo userInfo=api.getUserInfo();
            values.setDisplayName(userInfo.getNickname());
            values.setImageUrl(userInfo.getFigureurl_qq_1());
            values.setProfileUrl(null); //QQ 没有主页信息
            values.setProviderUserId(userInfo.getOpenId());
    }

    /**
     * 通过API拿到标准的用户信息
     * @param api
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    /**
     * 某些社交网站才会有这个 时间线
     * @param api
     * @param message
     */
    @Override
    public void updateStatus(QQ api, String message) {
        // do noting
    }
}
