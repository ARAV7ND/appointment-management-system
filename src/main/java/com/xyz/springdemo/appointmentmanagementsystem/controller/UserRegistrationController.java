package com.xyz.springdemo.appointmentmanagementsystem.controller;

import com.xyz.springdemo.appointmentmanagementsystem.dto.PatientDto;
import com.xyz.springdemo.appointmentmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new PatientDto());
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") PatientDto registrationDto){
        patientService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
