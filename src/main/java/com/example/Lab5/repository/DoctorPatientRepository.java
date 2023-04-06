package com.example.Lab5.repository;

import com.example.Lab5.entity.DoctorPatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorPatientRepository extends JpaRepository<DoctorPatient,Long> {
}
