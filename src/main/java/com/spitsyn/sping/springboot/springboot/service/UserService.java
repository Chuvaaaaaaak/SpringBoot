package com.spitsyn.sping.springboot.springboot.service;

import com.spitsyn.sping.springboot.springboot.model.User;

import java.util.List;


public interface UserService {

    List<User> allUsers();

    User getUser(long id);

    User saveUser(User user);

    boolean updateUser(User user, Long id);

    void deleteUser(long id);

    User findByUsername(String username);

}
