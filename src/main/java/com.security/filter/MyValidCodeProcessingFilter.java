//package com.security.filter;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class MyValidCodeProcessingFilter  extends UsernamePasswordAuthenticationFilter {
//
//
//    @Resource
//    private SessionRegistry sessionRegistry;
//
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        //省略部分代码......
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//        //用户名密码验证通过后,注册session
//        sessionRegistry.registerNewSession(request.getSession().getId(),token.getPrincipal());
//
//
//        //省略部分代码......
//        this.setDetails(request, token);
//        return this.getAuthenticationManager().authenticate(token);
//    }
//
//}
