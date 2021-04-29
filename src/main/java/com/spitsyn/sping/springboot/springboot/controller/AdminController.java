package com.spitsyn.sping.springboot.springboot.controller;


import com.spitsyn.sping.springboot.springboot.model.User;
import com.spitsyn.sping.springboot.springboot.service.RoleService;
import com.spitsyn.sping.springboot.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping ("/all")
    public String allUsers(ModelMap model) {
       List<User> allUsers =  userService.allUsers();
       model.addAttribute("allUsers", allUsers);
        return "all";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable long id, ModelMap model) {
        model.addAttribute("someUser", userService.getUser(id));
        return "show";
    }

    @GetMapping("/new")
    public String addNewUser(ModelMap model){
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.AllRoles());
        return "new";
    }
    @PostMapping("/new")
    public String createUser(@ModelAttribute("newUser") User user,
                             @RequestParam(value = "select_role", required = false) String[] role){
        userService.saveUser(user, role);
        return "redirect:/admin/all";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("allRoles", roleService.AllRoles());
        return "edit";
    }
    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "select_role", required = false) String[] role){
        userService.saveUser(user, role);
        return "redirect:/admin/all";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        userService.deleteUser(id);
        return "redirect:/admin/all";
    }


}
