package com.spitsyn.sping.springboot.springboot.service;



import com.spitsyn.sping.springboot.springboot.model.Role;
import com.spitsyn.sping.springboot.springboot.model.User;
import com.spitsyn.sping.springboot.springboot.repository.RoleRepository;
import com.spitsyn.sping.springboot.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).get();
    }


    @Override
    public void saveUser(User user, String[] roles) {
        Set<Role> updateRole = new HashSet<>();
        for(String role : roles) {
            if(role.equals("ROLE_ADMIN")) {
                updateRole.add(getRole(1L));
            } else {
                updateRole.add(getRole(2L));
            }
        }
        user.setRoles(updateRole);
        userRepository.save(user);
    }

    @Override
    public Role getRole(long id) {
        return roleRepository.findById(id).get();
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
