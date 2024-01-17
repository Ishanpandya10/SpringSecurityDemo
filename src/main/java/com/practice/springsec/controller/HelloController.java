package com.practice.springsec.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    @GetMapping(value = "/hello1")
    public String hello1(){
        System.out.println(SecurityContextHolder.getContext());
        return "Hello1";
    }

    @GetMapping(value = "/hello2")
    public String hello2(){
        System.out.println(SecurityContextHolder.getContext());
        return "Hello2";
    }
}