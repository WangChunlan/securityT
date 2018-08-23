package com.security.sms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.security.details.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @ClassName SmsCodeAuthenticationProvider
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/23 10:19
 * @Version 1.0
 * @Version 1.0
 **/
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetails userDetails;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken=(SmsCodeAuthenticationToken)authentication;

        UserDetails user=userDetails.loadUserByUsername((String) authenticationToken.getPrincipal());
        if(user ==null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsCodeAuthenticationToken authenticationTokenResult= new SmsCodeAuthenticationToken(user,user.getAuthorities());

        authenticationTokenResult.setDetails(authenticationToken.getDetails());
        return authenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public MyUserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(MyUserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
