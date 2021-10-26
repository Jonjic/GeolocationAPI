package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface UserRepository extends JpaRepository<User, Long> {
        //@Query ("SELECT s FORM User s WHERE s.email = ?")
        Optional <User> findUserByEmail(String email);
    }
