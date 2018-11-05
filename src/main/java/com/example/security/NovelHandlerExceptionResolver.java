package com.example.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

@Component
public class NovelHandlerExceptionResolver implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        System.out.println(ex);

        response.setStatus(HttpStatus.BAD_GATEWAY.value());
        try {
            response.getWriter().print("error con me no roi");
            response.flushBuffer();
        } catch (IOException e) {
//            e.printStackTrace();
        }

//        ModelAndView modelAndView = new ModelAndView("chichchoe");
//        modelAndView.setStatus(HttpStatus.BAD_GATEWAY);

//        return  modelAndView;
        return  null;
    }
}
