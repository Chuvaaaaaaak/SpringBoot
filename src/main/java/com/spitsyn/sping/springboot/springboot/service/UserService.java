package com.spitsyn.sping.springboot.springboot.service;

import com.spitsyn.sping.springboot.springboot.model.Role;
import com.spitsyn.sping.springboot.springboot.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<User> allUsers();

    User getUser(long id);

    void saveUser(User user, String[] roles);

    Role getRole(long id);

    void deleteUser(long id);

    User findByUsername(String username);

}
