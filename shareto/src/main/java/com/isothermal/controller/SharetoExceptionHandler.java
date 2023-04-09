package com.isothermal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class SharetoExceptionHandler {
    
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NullPointerException.class})
    public String handleExceptionNullPointer(Model m){
        m.addAttribute("msg", "Null pointer exception occured...");
        return "null_page";
    }
    
    @ExceptionHandler({NumberFormatException.class})
    public String handleExceptionNumberFormat(Model m){
        m.addAttribute("msg", "Number format exception occured...");
        return "null_page";
    }
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public String handleException(Model m){
        m.addAttribute("msg", "Exception occured...");
        return "null_page";
    }

}
