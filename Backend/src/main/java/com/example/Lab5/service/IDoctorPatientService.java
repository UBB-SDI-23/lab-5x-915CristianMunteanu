package com.example.Lab5.service;

import com.example.Lab5.entity.DoctorPatient;

import java.util.List;
import java.util.Optional;

public interface IDoctorPatientService {
    List<DoctorPatient> all();
    DoctorPatient newDoctorPatient(DoctorPatient newDoctorPatient);
    Optional<DoctorPatient> one(Long id);
    DoctorPatient replaceDoctorPatient(DoctorPatient newDoctorPatient,Long id);
    void deleteDoctorPatient(Long id);
}
