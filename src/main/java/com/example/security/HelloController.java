package com.example.security;

import java.security.Security;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

@RestController
public class HelloController {




    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JdbcUserDetailsManager userDetailsManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    WebApplicationContext webApplicationContext;


    @GetMapping("/filter")
    public String filter() {


        FilterChainProxy proxy = webApplicationContext.getBean("springSecurityFilterChain", FilterChainProxy.class);
        proxy.getFilterChains().forEach( t -> {
            t.getFilters().forEach(f -> System.out.println(f.getClass()));
            System.out.println("------------------------------");
        });

//        webApplicationContext.getServletContext().getFilterRegistrations().forEach((x, y) -> {
//            System.out.println("filter registration name: " + x);
//            System.out.println(y.getClassName());
//        });
        return "OK";
    }

    @GetMapping("/hello")
    public String hello(HttpSession session) {

        System.out.println("sesssion id: " + session.getId());
        Enumeration<String> enumeration = session.getAttributeNames();
        while(enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            System.out.println("session name: " + name + " : object id: "  + System.identityHashCode(session.getAttribute(name)));
        }
        System.out.println("current context object id: " + System.identityHashCode(SecurityContextHolder.getContext()));

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            System.out.println("is authenticated: " + authentication.isAuthenticated());
            System.out.println("principal name: " + authentication.getName());
            System.out.println("authentication str: " + authentication.toString());

            System.out.println("credentials: " + authentication.getCredentials());

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
        UserDetails user = User.withUsername(name)
                        .password(passwordEncoder.encode(password))
                        .authorities("ROLE_USER")
                        .build();
        userDetailsManager.createUser(user);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return "OK";
    }
}


