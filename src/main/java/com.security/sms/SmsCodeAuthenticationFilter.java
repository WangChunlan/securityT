package com.security.sms;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SmsCodeAuthenticationFilter
 * @Description TODO
 *      参照 普通的登录  过滤器  UsernamePasswordAuthenticationFilter
 *      copy  他的源码
 *
 *
 *
 * @Author wangchunlan
 * @Date 2018/8/23 9:38
 * @Version 1.0
 **/

/**
 * @Author wangchunlan
 * @Description //TODO
 *          1 认证请求的方法必须为POST
 *          2 从request中获取手机号
 *          3 封装成自己的Authentication 的实现类SmsCodeAuthenticationToken(未认证)
 *          4、调用AuthenticationManager的authenticate 方法进行验证（即SmsCodeAuthenticationProvider）
 * @Date 13:44 2018/8/23
 * @Param 
 * @return 
 **/ 

public class SmsCodeAuthenticationFilter  extends AbstractAuthenticationProcessingFilter {
    // 参数名字
    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "mobile";
    // 请求参数key
    private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;

    // 是否只支持post
    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        // 请求接口url
        super(new AntPathRequestMatcher("/login/mobile", "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            // 根据请求参数名，获取请求value
            String mobile = this.obtainMobile(request);
            if (mobile == null) {
                mobile = "";
            }

            mobile = mobile.trim();
            // 生成对应的authenticationToken
//            创建SmsCodeAuthenticationToken(未认证)
            SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
//设置用户信息
            setDetails(request, authRequest);
//            返回Authentication实例
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
