package com.security.filter;

import com.security.controller.ValidateCodeController;
import com.security.exception.ValidateCodeException;
import com.security.handler.MyAuthenticationFailureHandler;
import com.security.validateCode.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ValidateCodeFilter
 * @Description 验证码过滤器
 *          OncePerRequestFilter 保证我们的过滤器每次只会被调用一次
 *          他能够确保在一次请求只通过一次filter，而不需要重复执行
 * @Author wangchunlan
 * @Date 2018/8/20 9:55
 * @Version 1.0
 **/
public class ValidateCodeFilter  extends OncePerRequestFilter {

    protected Logger logger
            =LoggerFactory.getLogger(getClass());

    // todo 为什么 不用注解的方式 改用 get set方法? ==> 是应为webSecurityConfigure 中需要设置
    private MyAuthenticationFailureHandler failureHandler;

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    /**
     * @Author wangchunlan
     * @Description
     *              判断form表单 的请求  路径与post方式请求
     *
     * @Date 10:33 2018/8/20
     * @Param [httpServletRequest, httpServletResponse, filterChain]
     * @return void
     **/
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equals("/login/loginForm",httpServletRequest.getRequestURI())&&StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"post")){
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ServletRequestBindingException e) {
                e.printStackTrace();
            } catch (ValidateCodeException e) {
                failureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return ;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
    private void validate(ServletWebRequest request) throws ServletRequestBindingException, ValidateCodeException {
        ImageCode codeInSession =(ImageCode)sessionStrategy.getAttribute(request,ValidateCodeController.SESSION_KEY);

        String codeInRequest=ServletRequestUtils.getStringParameter(request.getRequest(),"imageCode");

        if(StringUtils.isBlank(codeInRequest)){
            logger.info("验证码的值不能为空");
            throw  new ValidateCodeException("验证码的值不能为空");
        }
        if(codeInSession==null){
            logger.info("验证码不存在");
            throw  new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);
            logger.info("验证码已过期");
            throw  new ValidateCodeException("验证码已过期");
        }
        if(!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            logger.info("验证码不匹配");
            throw  new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request,ValidateCodeController.SESSION_KEY);

    }

    public ValidateCodeFilter() {

    }
    public ValidateCodeFilter(MyAuthenticationFailureHandler failureHandler, SessionStrategy sessionStrategy) {
        this.failureHandler = failureHandler;
        this.sessionStrategy = sessionStrategy;
    }

    public MyAuthenticationFailureHandler getFailureHandler() {
        return failureHandler;
    }

    public void setFailureHandler(MyAuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }
}
