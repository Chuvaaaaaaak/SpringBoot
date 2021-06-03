package com.spitsyn.sping.springboot.springboot.service;


import com.spitsyn.sping.springboot.springboot.model.User;
import com.spitsyn.sping.springboot.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    //Optional
    @Override
    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public boolean updateUser(User user, Long id) {
      User updateUser =  userRepository.findById(id).get();
      if(updateUser.getId() == user.getId()) {
          userRepository.save(user);
          return true;
      }
        return false;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }


    // return Optional
    @Override
    public Optional<User> findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

}
