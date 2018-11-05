package com.example.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

//@RestController
public class NovelErrorController implements ErrorController {
    @GetMapping("/error")
    public String error(HttpServletResponse response, Exception ex, HttpServletRequest request) {
        System.out.println(ex.getClass());
        System.out.println(ex);
        return "ERROR of ERROR Controller";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
