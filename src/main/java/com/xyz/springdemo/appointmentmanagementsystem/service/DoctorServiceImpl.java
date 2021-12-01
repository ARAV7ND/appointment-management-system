package com.xyz.springdemo.appointmentmanagementsystem.service;

import com.xyz.springdemo.appointmentmanagementsystem.dao.AppointmentRepository;
import com.xyz.springdemo.appointmentmanagementsystem.dao.DoctorRepository;
import com.xyz.springdemo.appointmentmanagementsystem.dto.DoctorDto;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Appointment;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Doctor;
import com.xyz.springdemo.appointmentmanagementsystem.entity.Role;
import com.xyz.springdemo.appointmentmanagementsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void save(DoctorDto doctorDto) {
        Doctor doctor = new Doctor(doctorDto.getFirstName(),doctorDto.getLastName(),doctorDto.getUsername(),doctorDto.getSpecialist(),doctorDto.getAddress());
        doctor.setId(doctorDto.getId());
        User user = new User(doctorDto.getUsername(), bCryptPasswordEncoder.encode(doctorDto.getPassword()), Arrays.asList(new Role("ROLE_DOCTOR",doctorDto.getUsername())));
        user.setId(doctorDto.getId());
        doctorRepository.save(doctor);
        userService.save(user);

    }

    @Override
    public void deleteById(Integer integer) {
        Doctor doctor = findById(integer);
        doctorRepository.deleteById(doctor.getId());
        userService.deleteById(integer);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findById(int id) {
        Optional<Doctor> result = doctorRepository.findById(id);
        Doctor doctor;
        if(result.isPresent()){
            doctor = result.get();
        }else{
            throw new RuntimeException("404.!! Doctor Not found with Id "+id);
        }
        return doctor;
    }

    @Override
    public DoctorDto update(int id) {
        Doctor doctor = findById(id);
        User user = userService.findById(doctor.getId());
//        User user = userService.findByUsername(doctor.getEmail());
        DoctorDto doctorDto = new DoctorDto(doctor.getFirstName(),doctor.getLastName(),
                                            user.getUsername(),user.getPassword(),doctor.getAddress(),doctor.getSpecialist());
        doctorDto.setId(doctor.getId());
        return doctorDto;
    }

    @Override
    public void addAppointment(int doctorId, Appointment appointment) {
        Optional<Doctor> result = doctorRepository.findById(doctorId);
        Doctor doctor=null;
        if(result.isPresent()){
            doctor = result.get();
        }else{
            throw new RuntimeException("404 no doctor found with id "+doctorId);
        }
        doctor.addAppointment(appointment);
    }

    @Override
    public Doctor findByUsername(String username) {
        return doctorRepository.findByUsername(username);
    }

    @Override
    public List<Appointment> appointmentList(int id) {
        return appointmentRepository.findAllByDoctorId(id);
    }

    @Override
    public DoctorDto findByIdTwo(int id) {
        Doctor doctor = findById(id);
        User user = userService.findByUsername(doctor.getEmail());
        DoctorDto doctorDto =  new DoctorDto(doctor.getFirstName(),doctor.getLastName(),user.getUsername(),user.getUsername(), doctor.getAddress(), doctor.getSpecialist());
        doctorDto.setId(doctor.getId());
        return doctorDto;
    }
}
