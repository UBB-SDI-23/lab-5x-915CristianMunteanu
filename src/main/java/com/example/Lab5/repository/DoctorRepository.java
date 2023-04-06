package com.example.Lab5.repository;

import com.example.Lab5.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    List<Doctor> findDoctorBySalaryGreaterThan(int salary);
}
