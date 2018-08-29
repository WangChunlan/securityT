package com.security.filter;

import com.security.exception.ValidateCodeException;
import com.security.handler.MyAuthenticationFailureHandler;
import com.security.properties.MySecurityProperties;
import com.security.utils.SecurityConstants;
import com.security.validateCode.sms.SmsCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName ValidateCodeFilter
 * @Description 验证码过滤器
 * OncePerRequestFilter 保证我们的过滤器每次只会被调用一次
 * 他能够确保在一次请求只通过一次filter，而不需要重复执行
 * @Author wangchunlan
 * @Date 2018/8/20 9:55
 * @Version 1.0
 **/

public class SmsValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    protected Logger logger
            = LoggerFactory.getLogger(getClass());

    private MyAuthenticationFailureHandler failureHandler;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private AntPathMatcher pathMatcher = new AntPathMatcher();
    // 存拦截的url
    private Set<String> urls = new HashSet<>();

    private MySecurityProperties securityProperties;

    @Override
    public void afterPropertiesSet() throws ServletException {
        urls.add("/login/mobile");// 登录的请求是一定要做验证码的
        String url = securityProperties.getCode().getSms().getUrl();
        if (StringUtils.isNotBlank(url)) {
            String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(url, ",");
            for (String configUrl : configUrls) {
                urls.add(configUrl);
            }
        }
    }


    /**
     * 判断form表单 的请求  路径与post方式请求
     **/
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        boolean action = false;
        for (String url : urls) {
            if (pathMatcher.match(url, httpServletRequest.getRequestURI())) {
                action = true;
            }
        }

        if (action) {
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ServletRequestBindingException e) {
                e.printStackTrace();
            } catch (ValidateCodeException e) {
                failureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException, ValidateCodeException {
        SmsCode codeInSession = (SmsCode) sessionStrategy.getAttribute(request, SecurityConstants.DEFAULT_SESSION_KEY_FOR_CODE_SMS);

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");

        if (StringUtils.isBlank(codeInRequest)) {
            logger.info("验证码的值不能为空");
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (codeInSession == null) {
            logger.info("验证码不存在");
            throw new ValidateCodeException("验证码不存在");
        }
        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(request, SecurityConstants.DEFAULT_SESSION_KEY_FOR_CODE_SMS);
            logger.info("验证码已过期");
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            logger.info("验证码不匹配");
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, SecurityConstants.DEFAULT_SESSION_KEY_FOR_CODE_SMS);

    }

    public MyAuthenticationFailureHandler getFailureHandler() {
        return failureHandler;
    }

    public void setFailureHandler(MyAuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
        this.sessionStrategy = sessionStrategy;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public MySecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(MySecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public SmsValidateCodeFilter() {
    }

    public SmsValidateCodeFilter(MyAuthenticationFailureHandler failureHandler, SessionStrategy sessionStrategy) {
        this.failureHandler = failureHandler;
        this.sessionStrategy = sessionStrategy;
    }
}

