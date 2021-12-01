package com.xyz.springdemo.appointmentmanagementsystem.dao;

import com.xyz.springdemo.appointmentmanagementsystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    @Override
    List<Doctor> findAll();

    @Override
    Optional<Doctor> findById(Integer integer);

    @Query(value = "select * from doctor where email = ?1",nativeQuery = true)
    Doctor findByUsername(String username);


}
