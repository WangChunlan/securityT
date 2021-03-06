package com.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

@Component
public class MyUserDetails implements UserDetailsService, SocialUserDetailsService{
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 表单登录
     * todo: 注意UserDetails 是 SocialUserDetails的父类
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buildUser(username);
    }


    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return buildUser(userId);
    }
// https://blog.csdn.net/ayou_java/article/details/78902173
    private SocialUserDetails buildUser(String userId) {
        String password = passwordEncoder.encode("123456");
        return new SocialUser(userId, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
