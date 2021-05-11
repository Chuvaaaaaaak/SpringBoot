package com.spitsyn.sping.springboot.springboot.controller;


import com.spitsyn.sping.springboot.springboot.model.User;
import com.spitsyn.sping.springboot.springboot.service.RoleService;
import com.spitsyn.sping.springboot.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/admin")
    public String allUsers(ModelMap model) {
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.AllRoles());
        return "users";
    }

    @GetMapping("/user")
    public String user() {
        return "show";
    }
}
