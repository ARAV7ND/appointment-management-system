package com.xyz.springdemo.appointmentmanagementsystem.dao;

import com.xyz.springdemo.appointmentmanagementsystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    @Query(value = "select * from appointment where doctor_id = ?1",nativeQuery = true)
    List<Appointment> findAllByDoctorId(int doctorId);
}
