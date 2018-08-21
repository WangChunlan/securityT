package com.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HelloController {

    protected Logger log=LoggerFactory.getLogger(getClass());
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/judge")
    public  String judge(){
        return "judge";
    }



}
