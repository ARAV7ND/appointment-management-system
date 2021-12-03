package com.xyz.springdemo.appointmentmanagementsystem.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private String date;

    @Column(name = "appointment_start_time")
    private String startTime;

    @Column(name = "appointment_end_time")
    private String endTime;

    @Column(name = "patient_id")
    private int patientId;
    @Column(name = "doctor_Id")
    private int doctorId;

//    @ManyToOne
//    @JoinColumn(name = "doctor_id")
//    private Doctor doctor;
//
//    @ManyToOne
//    @JoinColumn(name = "patient_id")
//    private Patient patient;

    public Appointment() {
    }

    public Appointment(String date, String startTime, String endTime, int patientId, int doctorId) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

}
