package com.example.Lab5.service;

import com.example.Lab5.entity.LongDTO;
import com.example.Lab5.exceptions.DoctorNotFoundException;
import com.example.Lab5.repository.DoctorRepository;
import org.springframework.data.util.Pair;
import com.example.Lab5.entity.Doctor;
import com.example.Lab5.entity.Hospital;
import com.example.Lab5.entity.HospitalDTO;
import com.example.Lab5.exceptions.HospitalNotFoundException;
import com.example.Lab5.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospitalService implements IHospitalService {
    private final HospitalRepository repository;
    private final DoctorRepository doctorRepository;

    public HospitalService(HospitalRepository repository, DoctorRepository doctorRepository) {
        this.repository = repository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<HospitalDTO> all() {
        return repository.findAll().stream().map(HospitalDTO::new).collect(Collectors.toList());

    }
    public void bulkAddDoctors(List<LongDTO> ids, Long hospitalId){
        Optional<Hospital> hospital=repository.findById(hospitalId);
        if(hospital.isPresent()){
            Hospital h=hospital.get();
            for(LongDTO id: ids){
                Optional<Doctor> doctor=doctorRepository.findById(id.getId());
                if(doctor.isPresent()){
                    Doctor d=doctor.get();
                    d.setHospital(h);
                    doctorRepository.save(d);
                }
            }
        }else{
            throw new HospitalNotFoundException(hospitalId);
        }
    }
    @Override
    public Hospital newHospital(Hospital newHospital) {
        return repository.save(newHospital);
    }

    @Override
    public Hospital one(Long id) {
        return repository.findById(id).orElseThrow(() -> new HospitalNotFoundException(id));
    }

    @Override
    public Hospital replaceHospital(Hospital newHospital, Long id) {
        return repository.findById(id).map(hospital -> {
            hospital.setName(newHospital.getName());
            hospital.setAddress(newHospital.getAddress());
            hospital.setPhoneNumber(newHospital.getPhoneNumber());
            hospital.setNumberOfBeds(newHospital.getNumberOfBeds());
            return repository.save(hospital);
        }).orElseGet(() -> {
            newHospital.setId(id);
            return repository.save(newHospital);
        });
    }

    @Override
    public void deleteHospital(Long id) {
        repository.deleteById(id);
    }

    public List<Pair<Hospital, Doctor>> orderHospitalsByHighestDoctorSalary() {
        List<Pair<Hospital, Doctor>> result = new ArrayList<>();
        List<Hospital> hospitals = repository.findAll();
        hospitals.forEach(hospital -> hospital.getDoctors().stream().max(Comparator.comparingInt(Doctor::getSalary))
                .ifPresent(doctor -> result.add(Pair.of(hospital,doctor))));
        result.sort(Comparator.comparing(h->h.getSecond().getSalary()));
        return result;
    }
}
