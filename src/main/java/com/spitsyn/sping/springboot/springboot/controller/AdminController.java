package com.spitsyn.sping.springboot.springboot.controller;


import com.spitsyn.sping.springboot.springboot.model.User;
import com.spitsyn.sping.springboot.springboot.service.RoleService;
import com.spitsyn.sping.springboot.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

        return "/all";
    }

    @GetMapping("/user")
    public String user(Principal principal) {
        return "show";
    }
//    @GetMapping("/{id}")
//    public String getUser(@PathVariable long id, ModelMap model) {
//        model.addAttribute("someUser", userService.getUser(id));
//        return "show";
//    }

    //    @GetMapping("/new")
//    public String addNewUser(ModelMap model){
//        model.addAttribute("newUser", new User());
//        model.addAttribute("allRoles", roleService.AllRoles());
//        return "new";
//    }
    @PostMapping("/admin")
    public String createUser(@ModelAttribute("newUser") User user,
                             @RequestParam(value = "select_role", required = false) String[] role) {
        userService.saveUser(user, role);
        return "redirect:/admin";
    }

    //    @GetMapping("/{id}/edit")
//    public String editUser(@PathVariable("id") long id, Model model){
//        model.addAttribute("user", userService.getUser(id));
//        model.addAttribute("allRoles", roleService.AllRoles());
//        return "edit";
//    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                             @RequestParam(value = "select_role", required = false) String[] role) {
        userService.saveUser(user, role);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


}
