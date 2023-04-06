package com.example.Lab5.service;

import com.example.Lab5.entity.Doctor;
import com.example.Lab5.entity.SimpleDoctorDTO;

import java.util.List;

public interface IDoctorService {
    List<SimpleDoctorDTO> all();
    Doctor newDoctor(Doctor newDoctor,Long id);
    Doctor one(Long id);
    Doctor replaceDoctor(Doctor newDoctor,Long id);
    void deleteDoctor(Long id);
    List<SimpleDoctorDTO> findDoctorBySalaryGreaterThan(int salary);
}
