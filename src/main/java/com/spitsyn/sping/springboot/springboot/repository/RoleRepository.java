package com.spitsyn.sping.springboot.springboot.repository;

import com.spitsyn.sping.springboot.springboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
