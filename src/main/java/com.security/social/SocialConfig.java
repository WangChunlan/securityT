package com.security.social;

import com.security.properties.MySecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 将Connection 数据保存到数据库中DB
 * @EnableSocial
 * @ClassName SocialConfig
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/28 9:30
 * @Version 1.0
 **/
@Configuration
@EnableSocial
@Order(1)
public class SocialConfig extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MySecurityProperties securityProperties;
    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;



    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        // todo  Encryptors 后面要更改这个  为了安全
        JdbcUsersConnectionRepository repository=new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        if(connectionSignUp !=null){
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    @Bean
    public SpringSocialConfigurer mySocialSecurityConfig() {
        String filterProcessesUrl =
                securityProperties.getSocial().getFilterProcessesUrl();
        MySpringSocialConfigurer mySpringSocialConfigurer = new MySpringSocialConfigurer(filterProcessesUrl);

        mySpringSocialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpURL());
        return mySpringSocialConfigurer;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){

        return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator)){

        };
    }

}
