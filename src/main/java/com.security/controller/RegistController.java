package com.security.controller;

import com.security.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistController {

    protected Logger logger=LoggerFactory.getLogger(getClass());
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/regist")
    @ResponseBody
    public void regist(User user, HttpServletRequest request){
        // 注册逻辑
        logger.info("注册成功");
        // 不管是注册用户还是绑定用户，都会拿到一个用户的唯一标识
        String userId=user.getUsername();
        providerSignInUtils.doPostSignUp(userId,new ServletWebRequest(request));
    }
    @GetMapping("/demoSignUp")
    public String demoSignUp(){
       return "demoSignUp";

    }
    @GetMapping("/signUp")
    public String signUp(){
        return "signUp";

    }










}
