package com.xyz.springdemo.appointmentmanagementsystem.service;

import com.xyz.springdemo.appointmentmanagementsystem.dto.DoctorDto;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Appointment;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Doctor;

import java.util.*;
public interface DoctorService {
    void save(DoctorDto doctorDto);
    void deleteById(Integer integer);
    List<Doctor> findAll();
    Doctor findById(int id);
    DoctorDto update(int id);
    void addAppointment(int doctorId, Appointment appointment);
    Doctor findByUsername(String username);
    List<Appointment> appointmentList(int id);
    DoctorDto findByIdTwo(int id);

}
