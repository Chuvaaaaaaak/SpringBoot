package com.spitsyn.sping.springboot.springboot.repository;

import com.spitsyn.sping.springboot.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
