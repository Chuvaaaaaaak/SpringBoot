package com.spitsyn.sping.springboot.springboot.controller;


import com.spitsyn.sping.springboot.springboot.model.User;
import com.spitsyn.sping.springboot.springboot.service.RoleService;
import com.spitsyn.sping.springboot.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;



    @GetMapping()
    public String getUser(ModelMap model, Principal principal) {
        User someUser =userService.findByUsername(principal.getName());
        model.addAttribute("someUser", someUser);
        return "show";
    }
}
