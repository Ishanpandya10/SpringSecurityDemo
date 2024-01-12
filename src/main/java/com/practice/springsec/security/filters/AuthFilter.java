package com.practice.springsec.security.filters;

import com.practice.springsec.security.auth.CustomAuthManager;
import com.practice.springsec.security.auth.CustomAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final CustomAuthManager customAuthManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeaderValue = request.getHeader("Auth");
        CustomAuthentication customAuthentication = new CustomAuthentication(false, authHeaderValue);

        try {
            Authentication authenticate = customAuthManager.authenticate(customAuthentication);

            if (authenticate.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                filterChain.doFilter(request, response);
            }
        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

    }
}