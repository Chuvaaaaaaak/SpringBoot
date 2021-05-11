package com.spitsyn.sping.springboot.springboot.controller;


import com.spitsyn.sping.springboot.springboot.model.User;
import com.spitsyn.sping.springboot.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class MyRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/allusers")
    public List<User> list() {
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/newUser")
    public User addUserBd(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/edit")
    public User update(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
    }
}
