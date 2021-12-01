package com.xyz.springdemo.appointmentmanagementsystem.dao;

import com.xyz.springdemo.appointmentmanagementsystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    @Override
    List<Patient> findAll();

    @Override
    void deleteById(Integer integer);

    @Query(value = "select * from patient where email = ?1",nativeQuery = true)
    Patient findByEmail(String email);


}
