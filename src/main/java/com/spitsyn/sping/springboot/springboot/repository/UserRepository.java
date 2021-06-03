package com.spitsyn.sping.springboot.springboot.repository;

import com.spitsyn.sping.springboot.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u join fetch u.roles r where u.username = :username")
    Optional<User> findByUsername(String username);

    @Override
    @Query("from User u join fetch u.roles")
    List<User> findAll();
}
