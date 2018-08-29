package com.security.social.qq.connet;

import com.security.social.qq.api.QQ;
import com.security.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

/**
 * @ClassName QQAdapter
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/28 9:14
 * @Version 1.0
 **/
public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {

            QQUserInfo userInfo=api.getUserInfo();
            values.setDisplayName(userInfo.getNickname());
            values.setImageUrl(userInfo.getFigureurl_qq_1());
            values.setProfileUrl(null); //主页信息
            values.setProviderUserId(userInfo.getOpenId());// 在接口中并没有这个参数

    }

    /**
     *
     * @param api
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    /**
     * 某些社交网站才会有这个
     * @param api
     * @param message
     */
    @Override
    public void updateStatus(QQ api, String message) {
        // do noting
    }
}
