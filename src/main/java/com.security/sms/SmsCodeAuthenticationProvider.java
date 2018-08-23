package com.security.sms;

import com.security.details.MyUserDetails;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @ClassName SmsCodeAuthenticationProvider
 *          对应用户名密码登录的 DaoAuthenticationProvider
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/23 10:19
 * @Version 1.0
 * @Version 1.0
 **/
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {



    private UserDetailsService userDetailsService; // 这个是spring框架自带的

    /**
     *  身份逻辑验证
     *
     **/
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken=(SmsCodeAuthenticationToken)authentication;

//        自定义的userDetailsService 认证
        UserDetails user=userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if(user ==null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        //如果user不为空重新构建SmsCodeAuthenticationToken（已认证）
        SmsCodeAuthenticationToken authenticationTokenResult= new SmsCodeAuthenticationToken(user,user.getAuthorities());

        authenticationTokenResult.setDetails(authenticationToken.getDetails());
        return authenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
