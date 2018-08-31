package com.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuccessController {
    @GetMapping("/")
    public String demoSignUp(){
        return "success";

    }
}
