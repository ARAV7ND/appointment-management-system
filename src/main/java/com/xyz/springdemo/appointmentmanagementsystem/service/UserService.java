package com.xyz.springdemo.appointmentmanagementsystem.service;

import com.xyz.springdemo.appointmentmanagementsystem.dto.PatientDto;
import com.xyz.springdemo.appointmentmanagementsystem.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.*;
public interface UserService extends UserDetailsService {
    User save(User user);
    List<User> findAll();
    User findById(int id);
    User findByUsername(String username);
    void deleteById(int id);
}
