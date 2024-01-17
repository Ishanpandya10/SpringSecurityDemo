package com.practice.springsec.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {
    @GetMapping(value = "/demo1")
    public String demo1(){
        System.out.println(SecurityContextHolder.getContext());
        return "Demo1 GET";
    }

    @GetMapping(value = "/demo2")
    public String demo2(){
        System.out.println(SecurityContextHolder.getContext());
        return "Demo2 GET";
    }

    @PostMapping(value = "/demo3")
    public String demo3(){
        System.out.println(SecurityContextHolder.getContext());
        return "Demo3 POST";
    }
}
