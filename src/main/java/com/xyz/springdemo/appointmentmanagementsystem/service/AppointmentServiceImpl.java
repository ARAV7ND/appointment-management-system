package com.xyz.springdemo.appointmentmanagementsystem.service;

import com.xyz.springdemo.appointmentmanagementsystem.dao.AppointmentRepository;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
