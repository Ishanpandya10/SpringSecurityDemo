package com.practice.springsec.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello(){
        System.out.println(SecurityContextHolder.getContext());
        return "called API";
    }
}