package com.security.sms;

import com.security.details.MyUserDetails;
import com.security.handler.MyAuthenticationFailureHandler;
import com.security.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @ClassName SmsCodeAuthenticationSecurityConfig
 * @Description TODO
 * @Author wangchunlan
 * @Date 2018/8/23 10:40
 * @Version 1.0
 **/
@Component
public class SmsCodeAuthenticationSecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {
    @Autowired
    private MyAuthenticationSuccessHandler successHandler;
    @Autowired
    private MyAuthenticationFailureHandler faileHandler;

    @Autowired
    private MyUserDetails myUserDetails;




    @Override
    public void configure(HttpSecurity builder) throws Exception {
//        自定义SmsCodeAuthenticationFilter过滤器
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter=new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(faileHandler);
//设置自定义SmsCodeAuthenticationProvider 的认证器userDetailsService
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider=new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(myUserDetails);
//  在UsernamePasswordAuthenticationFilter过滤器前执行
        builder.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
