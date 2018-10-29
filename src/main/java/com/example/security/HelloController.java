package com.example.security;

import javax.swing.plaf.synth.SynthTextAreaUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsManager userDetailsManager;


    @GetMapping("/hello")
    public String hello() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            System.out.println("is authenticated: " + authentication.isAuthenticated());
            System.out.println("principal name: " + authentication.getName());
            System.out.println("authentication str: " + authentication.toString());

            System.out.println("principal: " + authentication.getPrincipal());

            if (UserDetails.class.isAssignableFrom(authentication.getPrincipal().getClass())) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                System.out.println("principal username: " + userDetails.getUsername());
                System.out.println("principal password: " + userDetails.getPassword());
                System.out.println("principal authorities: " + userDetails.getAuthorities());
            }

        }
        return "hello world";
    }

    @GetMapping("/chich")
    public String createUser(@RequestParam String name, @RequestParam String password) {
        userDetailsManager.createUser(
                User.withUsername(name)
                .password(password)
                .authorities("ROLE_USER")
                .build()
        );
        return "OK";
    }
}


