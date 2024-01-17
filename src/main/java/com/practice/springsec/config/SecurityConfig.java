package com.practice.springsec.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    //CUSTOM FILTER
    //private final AuthFilter authFilter;

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        //CUSTOM FILTER
        /*return http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                //.authorizeHttpRequests(AbstractRequestMatcherRegistry::anyRequest)
                .build();*/

        return http.httpBasic(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable)
                //Mvc Matcher
                .authorizeHttpRequests(matcher -> matcher.requestMatchers(mvc.pattern(HttpMethod.GET, "/demo/**"))
                        .hasRole("Admin"))
                /*.authorizeHttpRequests(matcher -> matcher.requestMatchers(HttpMethod.GET, "/demo/**").hasRole("Admin"))*/
                //ANT matcher
                /*.authorizeHttpRequests(matcher -> matcher.requestMatchers(AntPathRequestMatcher.antMatcher("/demo/demo1"))
                        .hasRole("Admin"))*/
                .authorizeHttpRequests(matcher -> matcher.anyRequest().authenticated())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails ip = User.withUsername("bill")
                .password(passwordEncoder().encode("bill"))
                .roles("Admin").build();

        UserDetails mp = User.withUsername("ball")
                .password(passwordEncoder().encode("ball"))
                .roles("User").build();

        return new InMemoryUserDetailsManager(ip, mp);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
