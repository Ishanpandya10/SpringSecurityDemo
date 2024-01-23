package com.practice.springsec.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    @PreAuthorize(value = "hasRole('User')") //SpEL from object SecurityExpressionRoot
    @GetMapping(value = "/hello1")
    public String hello1(){
        System.out.println(SecurityContextHolder.getContext());
        return "Hello1";
    }

    @PreAuthorize(value = "hasRole('User')") //SpEL from object SecurityExpressionRoot
    @GetMapping(value = "/hello2")
    public String hello2(){
        System.out.println(SecurityContextHolder.getContext());
        return "Hello2";
    }

    @PreAuthorize(value = "@customMethodLevelSecurityHandler.check(authentication)")
    @GetMapping(value = "/hello3")
    public String hello3(){
        System.out.println(SecurityContextHolder.getContext());
        return "Hello3";
    }
}