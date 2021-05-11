package com.spitsyn.sping.springboot.springboot.service;


import com.spitsyn.sping.springboot.springboot.model.User;
import com.spitsyn.sping.springboot.springboot.repository.RoleRepository;
import com.spitsyn.sping.springboot.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User saveUser(User user) {
       return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

}
