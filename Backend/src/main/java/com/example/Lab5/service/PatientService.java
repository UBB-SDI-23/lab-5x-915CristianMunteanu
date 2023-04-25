package com.example.Lab5.service;

import com.example.Lab5.entity.Patient;
import com.example.Lab5.exceptions.PatientNotFoundException;
import com.example.Lab5.repository.DoctorRepository;
import com.example.Lab5.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientService implements IPatientService{
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public PatientService(PatientRepository repository, DoctorRepository doctorRepository) {
        this.patientRepository = repository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Patient> all() {
        return this.patientRepository.findAll();
    }

    @Override
    public Patient newPatient(Patient newPatient) {
        return patientRepository.save(newPatient);
    }

    @Override
    public Patient one(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id));
    }

    @Override
    public Patient replacePatient(Patient newPatient, Long id) {
        return patientRepository.findById(id).map(patient -> {patient.setFirstName(newPatient.getFirstName());
            patient.setLastName(newPatient.getLastName());
            patient.setIllness(newPatient.getIllness());
            patient.setPhoneNumber(newPatient.getPhoneNumber());
            patient.setRoomNumber(newPatient.getRoomNumber());
            return patientRepository.save(patient);
        }).orElseGet(() -> {newPatient.setId(id);
            return patientRepository.save(newPatient);
        });
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
