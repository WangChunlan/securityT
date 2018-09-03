package com.security.social.qq.config;

import com.security.properties.MySecurityProperties;

import com.security.social.qq.connet.QQConnectionFactory;
import com.security.social.qq.properties.QQProperties;
import com.security.social.qq.view.ConnectionQQView;
import com.security.social.weixin.view.ConnectionWXView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * @ClassName QQAutoConfig
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/28 13:37
 * @Version 1.0
 **/

/**
 * @ConditionalOnProperty 只有当里面的com.security.social.qq.app-id 在配置文件中被配置了，这个文件才生效。
 */
@Configuration
@ConditionalOnProperty(prefix = "com.security.social.qq",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private MySecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties=securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProviderId(),qqProperties.getAppId(),qqProperties.getAppSecret());
    }
    /**
     * 绑定与解绑
     * @return
     */
    @Bean({"connect/qqConnect","connect/qqConnected"})
    @ConditionalOnMissingBean(name="qqConnectedView")
    public View qqConnectedView(){
        return new ConnectionQQView();
    }
}
