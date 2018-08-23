package com.security.sms;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SmsCodeAuthenticationFilter
 * @Description TODO
 *      参照 普通的登录  过滤器  UsernamePasswordAuthenticationFilter
 *      copy  他的源码
 *
 * @Author wangchunlan
 * @Date 2018/8/23 9:38
 * @Version 1.0
 **/
public class SmsCodeAuthenticationFilter  extends AbstractAuthenticationProcessingFilter {
    // 参数名字
    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "mobile";
    private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;

    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login/mobile", "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String mobile = this.obtainMobile(request);
            if (mobile == null) {
                mobile = "";
            }

            mobile = mobile.trim();
            SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

   /**
    * @Author wangchunlan
    * @Description //TODO
    *   获取手机号的方法
    * @Date 10:18 2018/8/23
    * @Param [request]
    * @return java.lang.String
    **/
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }





    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getMobileParameter() {
        return mobileParameter;
    }

    public void setMobileParameter(String mobileParameter) {
        this.mobileParameter = mobileParameter;
    }
}
