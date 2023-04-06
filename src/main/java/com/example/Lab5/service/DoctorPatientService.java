package com.example.Lab5.service;

import com.example.Lab5.entity.Doctor;
import com.example.Lab5.entity.DoctorPatient;
import com.example.Lab5.entity.Patient;
import com.example.Lab5.exceptions.DoctorNotFoundException;
import com.example.Lab5.exceptions.PatientNotFoundException;
import com.example.Lab5.repository.DoctorPatientRepository;
import com.example.Lab5.repository.DoctorRepository;
import com.example.Lab5.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DoctorPatientService implements IDoctorPatientService{
    private DoctorPatientRepository doctorPatientRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    public DoctorPatientService(DoctorPatientRepository doctorPatientRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorPatientRepository = doctorPatientRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<DoctorPatient> all() {
        return doctorPatientRepository.findAll();
    }

    @Override
    public DoctorPatient newDoctorPatient(DoctorPatient newDoctorPatient) {
        return doctorPatientRepository.save(newDoctorPatient);
    }

    @Override
    public Optional<DoctorPatient> one(Long id) {
        return doctorPatientRepository.findById(id);
    }

    @Override
    public DoctorPatient replaceDoctorPatient(DoctorPatient newDoctorPatient, Long id) {
        return null;
    }

    @Override
    public void deleteDoctorPatient(Long id) {

    }
    public void addRelationship(DoctorPatient newDoctorPatient,Long doctorId, Long patientId){
        Optional<Doctor> d=this.doctorRepository.findById(doctorId);
        Optional<Patient> p=this.patientRepository.findById(patientId);
        if(d.isPresent()) {
            if (p.isPresent()) {
                newDoctorPatient.setDoctor(d.get());
                newDoctorPatient.setPatient(p.get());
                doctorPatientRepository.save(newDoctorPatient);
            }
            else throw new PatientNotFoundException(patientId);
        }
        else throw new DoctorNotFoundException(doctorId);
    }
}
