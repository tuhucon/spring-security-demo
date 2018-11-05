package com.example.security;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class NovelControllerAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String error1(RuntimeException ex, WebRequest request) {
        System.out.println(ex);
        return "error";
    }
}
