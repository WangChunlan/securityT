///**
// *
// */
//package com.security.newcode;
//
//import com.security.details.MyUserDetails;
//import com.security.handler.MyAuthenticationFailureHandler;
//import com.security.handler.MyAuthenticationSuccessHandler;
//import com.security.sms.SmsCodeAuthenticationFilter;
//import com.security.sms.SmsCodeAuthenticationProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
///**
// * @author zhailiang
// */
//@Component
//public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//    @Autowired
//    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
//
//    @Autowired
//    private MyAuthenticationFailureHandler authenticationFailureHandler;
//
//    @Autowired
//    private MyUserDetails myUserDetailService;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
//        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
//        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
//        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
//        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
//
//        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
//        smsCodeAuthenticationProvider.setUserDetailsService(myUserDetailService);
//
//        http.authenticationProvider(smsCodeAuthenticationProvider)
//                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
//}
