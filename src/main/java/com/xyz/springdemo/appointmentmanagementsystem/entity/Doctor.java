package com.xyz.springdemo.appointmentmanagementsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "specialist")
    private String specialist;
    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.LAZY
            ,cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.PERSIST})
    @JoinColumn(name = "doctor_id")
    private List<Appointment> appointments;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String email, String specialist, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.specialist = specialist;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
    public void addAppointment(Appointment appointment){
        if(appointments==null){
            appointments = new ArrayList<>();
        }
        appointments.add(appointment);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
