package com.xyz.springdemo.appointmentmanagementsystem.service;

import com.xyz.springdemo.appointmentmanagementsystem.dao.PatientRepository;
import com.xyz.springdemo.appointmentmanagementsystem.dto.PatientDto;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Appointment;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Patient;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Role;
import com.xyz.springdemo.appointmentmanagementsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(PatientDto patientDto) {
        Patient patient = new Patient(patientDto.getFirstName(),patientDto.getLastName(),patientDto.getUsername(),patientDto.getPhone());
        User user = new User(patientDto.getUsername(), bCryptPasswordEncoder.encode(patientDto.getPassword()), Arrays.asList(new Role("ROLE_USER", patientDto.getUsername())));
        patient.setId(patientDto.getId());
        user.setId(patientDto.getId());
        patientRepository.save(patient);
        userService.save(user);
    }

    @Override
    public void deleteById(Integer integer) {
        patientRepository.deleteById(integer);
        userService.deleteById(integer);
    }

    @Override
    public Patient findById(int id) {
        Optional<Patient> result = patientRepository.findById(id);
        Patient patient;
        if(result.isPresent()){
            patient = result.get();
        }else{throw new RuntimeException("404!! No Patient found with Id "+id);}
        return patient;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public PatientDto update(int id) {
        Patient patient = findById(id);
        User user = userService.findById(patient.getId());

        PatientDto patientDto = new PatientDto(patient.getFirstName(),patient.getLastName(),
                                     patient.getPhone(),user.getUsername(), user.getPassword());
        patientDto.setId(patient.getId());
        return patientDto;
    }

    @Override
    public Patient findByUsername(String username) {
        return patientRepository.findByEmail(username);
    }

    @Override
    public void addAppointment(int patientId,Appointment appointment) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        Patient patientObj = null;
        if(patient.isPresent()){
            patientObj = patient.get();
            patientObj.addAppointment(appointment);
        }else{
            throw new RuntimeException("No Patient found with id "+patientId);
        }
    }
}
