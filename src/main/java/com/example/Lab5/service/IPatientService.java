package com.example.Lab5.service;

import com.example.Lab5.entity.Doctor;
import com.example.Lab5.entity.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> all();
    Patient newPatient(Patient newPatient);
    Patient one(Long id);
    Patient replacePatient(Patient newPatient,Long id);
    void deletePatient(Long id);
}
