package com.practice.springsec.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomMethodLevelSecurityHandler {
    public boolean check(Authentication authentication) {
        System.out.println("authentication: "+ authentication);
        System.out.println("SecurityContextHolder.getContext().getAuthentication()" + SecurityContextHolder.getContext().getAuthentication());
        System.out.println("Equals: " + SecurityContextHolder.getContext().getAuthentication().equals(authentication));
        System.out.println("Principal: " +authentication.getPrincipal());
        System.out.println("Authorities: " + authentication.getAuthorities());
        System.out.println("Credentials: " + authentication.getCredentials());
        System.out.println("Details: "+authentication.getDetails());
        return true;
    }
}
