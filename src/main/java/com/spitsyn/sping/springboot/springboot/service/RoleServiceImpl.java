package com.spitsyn.sping.springboot.springboot.service;

import com.spitsyn.sping.springboot.springboot.model.Role;
import com.spitsyn.sping.springboot.springboot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> AllRoles() {
        return roleRepository.findAll();
    }
}
