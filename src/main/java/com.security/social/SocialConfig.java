package com.security.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;
//
import javax.sql.DataSource;

/**
 * ， Spring Boot 2.0中已删除Spring Social的自动配置。
 * SocialConfigurerAdapter
 * org.springframework.social.config.annotation.SocialConfigurerAdapter
 *
 * @EnableSocial
 * @ClassName SocialConfig
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/28 9:30
 * @Version 1.0
 **/
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        // todo  Encryptors 后面要更改这个  为了安全
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    }

    @Bean
    public SpringSocialConfigurer imoocSocialSecurityConfig() {
        return new SpringSocialConfigurer();
    }

}
