package com.spitsyn.sping.springboot.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class AdminController {

    //JSON
    @GetMapping("/admin")
    public String allUsers() {
        return "admin";
    }

    //add view controller метод в конфигурации Spring MVC
    @GetMapping("/user")
    public String user() {
        return "show";
    }
}