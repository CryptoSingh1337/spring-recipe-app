package com.saransh.springrecipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by CryptoSingh1337 on 7/13/2021
 */
@Slf4j
@Controller
public class NotFoundErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError() {
        log.error("Handling 404 error");
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("error_code", 404);
        mav.addObject("exception", new RuntimeException("Page does not exists"));
        return mav;
    }
}
