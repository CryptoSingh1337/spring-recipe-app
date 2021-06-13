package com.saransh.springrecipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CryptoSingh1337 on 6/13/2021
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage() {
        return "index";
    }
}
