package com.xyz.springdemo.appointmentmanagementsystem.dao;

import com.xyz.springdemo.appointmentmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Override
    Optional<User> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    User findByUsername(String username);

}
