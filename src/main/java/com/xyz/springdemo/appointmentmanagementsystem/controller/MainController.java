package com.xyz.springdemo.appointmentmanagementsystem.controller;

import com.xyz.springdemo.appointmentmanagementsystem.dao.UserRepository;
import com.xyz.springdemo.appointmentmanagementsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(Model model,Principal principal){
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user",user);
       return "/home";
    }
    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user",user);
        return "admin/admin-home";
    }
    @GetMapping("/doctor")
    public String doctor(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user",user);
        return "doctor/doctor-home";
    }
    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user",user);
        return "redirect:/patient/home";
    }

}
