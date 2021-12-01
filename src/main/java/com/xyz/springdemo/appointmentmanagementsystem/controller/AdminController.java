package com.xyz.springdemo.appointmentmanagementsystem.controller;

import com.xyz.springdemo.appointmentmanagementsystem.dto.DoctorDto;
import com.xyz.springdemo.appointmentmanagementsystem.dto.PatientDto;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Doctor;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Patient;
import com.xyz.springdemo.appointmentmanagementsystem.service.DoctorService;
import com.xyz.springdemo.appointmentmanagementsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/home")
    public String home(Model model, Principal principal){
        model.addAttribute("user",new Patient("Admin","site","admin@gmail.com","123456"));
        return "admin/admin-home";
    }
    @GetMapping("/add")
    public String addDoctor(Model model){
        model.addAttribute("user",new DoctorDto());
        return "admin/doctor-registration";
    }

    @PostMapping("/saveDoctor")
    public String saveDoctor(@ModelAttribute("user") DoctorDto doctorDto){
        doctorService.save(doctorDto);
        return "redirect:/admin/doctorList";
    }
    @GetMapping("/doctorList")
    public String getDoctorsList(Model model){
        List<Doctor> doctorList = doctorService.findAll();
        model.addAttribute("users",doctorList);
        return "admin/doctor-list";
    }
    @GetMapping("/deleteDoctor")
    public String deleteDoctor(@RequestParam("id") int id){
        doctorService.deleteById(id);
        return "redirect:/admin/doctorList";
    }
    @GetMapping("/updateDoctor")
    public String updateDoctor(@RequestParam("id") int id,Model model){
        DoctorDto doctorDto = doctorService.update(id);
//        DoctorDto doctorDto = doctorService.findByIdTwo(id);
        model.addAttribute("user",doctorDto);
        return "admin/doctor-registration";
    }

    @GetMapping("/patientList")
    public String patientList(Model model){
        List<Patient> patient = patientService.findAll();
        model.addAttribute("users",patient);
        return "admin/patient-list";
    }
    @GetMapping("/updatePatient")
    public String updatePatient(@RequestParam("id") int id,Model model){
        PatientDto patientDto = patientService.update(id);
        model.addAttribute("user",patientDto);
        return "admin/patient-registration";
    }
    @PostMapping("/savePatient")
    public String savePatient(@ModelAttribute("user") PatientDto patientDto){
        patientService.save(patientDto);
        return "redirect:/admin/patientList";
    }
    @GetMapping("/deletePatient")
    public String deletePatient(@RequestParam("id") int id){
        patientService.deleteById(id);
        return "redirect:/admin/patientList";
    }
}
