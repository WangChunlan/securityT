package com.security;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//@EnableSwagger2
public class SpringBootApplicationDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationDemo.class,args);
    }


    @GetMapping("/hello")
    public String hello(){
        return "hello spring security.";
    }





























}
