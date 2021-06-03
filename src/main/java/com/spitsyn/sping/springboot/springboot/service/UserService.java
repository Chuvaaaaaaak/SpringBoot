package com.spitsyn.sping.springboot.springboot.service;

import com.spitsyn.sping.springboot.springboot.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> allUsers();

    Optional<User> getUser(long id);

    User saveUser(User user);

    boolean updateUser(User user, Long id);

    void deleteUser(long id);

    Optional<User> findByUsername(String username);

}
