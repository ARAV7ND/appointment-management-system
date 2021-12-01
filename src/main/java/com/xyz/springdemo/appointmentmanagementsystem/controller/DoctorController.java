package com.xyz.springdemo.appointmentmanagementsystem.controller;

import com.xyz.springdemo.appointmentmanagementsystem.entity.Appointment;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Doctor;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Patient;
import com.xyz.springdemo.appointmentmanagementsystem.entity.User;
import com.xyz.springdemo.appointmentmanagementsystem.service.DoctorService;
import com.xyz.springdemo.appointmentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(Model model){
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Doctor doctor = null;
        if(obj instanceof UserDetails){
            String username = ((UserDetails)obj).getUsername();
            doctor = doctorService.findByUsername(username);
        }
        model.addAttribute("user",doctor);
        return "doctor/doctor-home";
    }
    @GetMapping("/appointmentList")
    public String appointmentList(@RequestParam("id") int id,Model model){
        List<Appointment> appointmentList = doctorService.appointmentList(id);
        model.addAttribute("appointmentList",appointmentList);
        return "doctor/list";
    }
}
