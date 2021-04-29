package com.spitsyn.sping.springboot.springboot.repository;

import com.spitsyn.sping.springboot.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User u JOIN FETCH u.roles as roles WHERE u.username = :username")
    User findByUsername(String username);

}
