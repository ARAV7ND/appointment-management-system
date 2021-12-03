package com.xyz.springdemo.appointmentmanagementsystem.controller;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Appointment;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Doctor;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Patient;
import com.xyz.springdemo.appointmentmanagementsystem.service.AppointmentService;
import com.xyz.springdemo.appointmentmanagementsystem.service.DoctorService;
import com.xyz.springdemo.appointmentmanagementsystem.service.PatientService;
import com.xyz.springdemo.appointmentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/home")
    public String home(Model model){
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = null;
        if(obj instanceof UserDetails){
            String username = ((UserDetails)obj).getUsername();
            patient = patientService.findByUsername(username);
        }

        model.addAttribute("user",patient);
        return "patient/patient-home";
    }

    @GetMapping("/doctorList")
    public String getDoctorsList(Model model) {
        List<Doctor> doctorList = doctorService.findAll();
        model.addAttribute("users", doctorList);
        return "patient/doctor-list";
    }

    @GetMapping("/appointmentForm")
    public String showAppointmentForm(@RequestParam("id") int id, Model model){
        Appointment appointment = new Appointment();
        appointment.setDoctorId(id);
        model.addAttribute("appointment",appointment);
        return "patient/appointment-form";
    }

    @PostMapping("/addAppointment")
    public String addAppointment(@ModelAttribute("appointment") Appointment appointment){
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int patientId = 0;
        if(obj instanceof UserDetails){
            String username = ((UserDetails)obj).getUsername();
            Patient patient = patientService.findByUsername(username);
            patientId = patient.getId();
        }
        appointment.setPatientId(patientId);
        patientService.addAppointment(patientId,appointment);
        doctorService.addAppointment(appointment.getDoctorId(),appointment);
        appointmentService.save(appointment);
        return "redirect:/patient/home";
    }
}























//    @GetMapping("/registration")
//    public String patientRegistration(Model model){
//        model.addAttribute("user",new PatientDto());
//        return "patient/patient-registration";
//    }
//
//    @PostMapping("/add")
//    public String addPatientAccount(@ModelAttribute("user") PatientDto patientDto){
//        patientService.save(patientDto);
//        return "redirect:/patient/registration?success";
//    }





