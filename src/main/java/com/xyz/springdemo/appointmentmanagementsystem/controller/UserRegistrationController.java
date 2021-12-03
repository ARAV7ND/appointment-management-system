package com.xyz.springdemo.appointmentmanagementsystem.controller;

import com.xyz.springdemo.appointmentmanagementsystem.dto.PatientDto;
import com.xyz.springdemo.appointmentmanagementsystem.entity.User;
import com.xyz.springdemo.appointmentmanagementsystem.service.PatientService;
import com.xyz.springdemo.appointmentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,trimmerEditor);
    }
    @GetMapping("/")
    public String home(){
        return "/home";
    }
    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new PatientDto());
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("user") PatientDto registrationDto, BindingResult bindingResult){
        if(userService.usernameAlreadyExists(registrationDto.getUsername())){
            bindingResult.addError(new FieldError("user","username","username already exists"));
        }
        if (bindingResult.hasErrors()){
            return "registration";
        }
        patientService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
