package com.xyz.springdemo.appointmentmanagementsystem.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public String exceptionHandler(MyRuntimeException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error/error-page";
    }


    @org.springframework.web.bind.annotation.ExceptionHandler
    public String exceptionHandler(Exception e, Model model){
        model.addAttribute("message",e.getMessage());
        return "error/error-page";
    }

}
