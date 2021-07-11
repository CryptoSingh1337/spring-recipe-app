package com.saransh.springrecipeapp.controllers;

import com.saransh.springrecipeapp.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by CryptoSingh1337 on 7/11/2021
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception e) {
        log.error("Handling not found exception");
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("error_code", "404");
        mav.addObject("exception", e);
        return mav;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException() {
        log.error("Handling Number Format exception");
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("error_code", "400");
        mav.addObject("exception", new RuntimeException("Invalid ID. ID must be a Number"));
        return mav;
    }
}
