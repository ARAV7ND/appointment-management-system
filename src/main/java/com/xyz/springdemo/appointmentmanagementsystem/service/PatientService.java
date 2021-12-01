package com.xyz.springdemo.appointmentmanagementsystem.service;

import com.xyz.springdemo.appointmentmanagementsystem.dto.PatientDto;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Appointment;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Patient;
import java.util.*;
public interface PatientService {
    void save(PatientDto patient);
    void deleteById(Integer integer);
    Patient findById(int id);
    List<Patient> findAll();
    PatientDto update(int id);
    Patient findByUsername(String username);
    void addAppointment(int doctorId,Appointment appointment);

}
