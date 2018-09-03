package com.security.controller;

import com.security.utils.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloController {

    protected Logger log=LoggerFactory.getLogger(getClass());
    @GetMapping(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
    public String login() {
        return SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM;
    }

    @GetMapping("/judge")
    public  String judge(){
        return "judge";
    }

    @GetMapping("/bangding")
    public  String bangding(){
        return "bangding";
    }

}
