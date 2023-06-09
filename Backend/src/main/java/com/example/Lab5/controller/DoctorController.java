package com.example.Lab5.controller;

import com.example.Lab5.entity.*;
import com.example.Lab5.service.DoctorService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/doctors")
public class DoctorController {
    private final DoctorService service;

    public DoctorController( DoctorService service) {
        this.service = service;
    }

    @GetMapping
    List<SimpleDoctorDTO> all(@RequestParam(required = false) Optional<Integer> salary){
        List<SimpleDoctorDTO> doctors;
        if(salary.isPresent())
            doctors=service.findDoctorBySalaryGreaterThan(salary.get());
        else
            doctors=service.all();
        return doctors;
    }

    @PostMapping("/{id}")
    com.example.Lab5.entity.DoctorWithIdDTO newDoctor(@Valid @RequestBody Doctor newDoctor, @PathVariable Long id){
        Doctor d=service.newDoctor(newDoctor,id);
        return new com.example.Lab5.entity.DoctorWithIdDTO(d);
    }

    @PostMapping
    SimpleDoctorDTO newDoctorWithoutHospitalAssigned(@Valid @RequestBody Doctor newDoctor){
        Doctor d=service.newDoctorWithoutHospitalAssigned(newDoctor);
        return new SimpleDoctorDTO(d);
    }

    @GetMapping("/filter")
    public List<DoctorWithNumberOfPatientsDTO> orderDoctorsByNumberOfPatients(){
        List<Pair<Doctor, Integer>> result=service.orderDoctorsByNumberOfPatients();
        List<DoctorWithNumberOfPatientsDTO> toReturn=new ArrayList<>();
        for (Pair<Doctor, Integer> res:result) {
            toReturn.add(new DoctorWithNumberOfPatientsDTO(res.getFirst(), res.getSecond()));
        }
        return toReturn;
    }

    @GetMapping("/{id}")
    DoctorWithAllDataDTO one(@PathVariable Long id){
        Doctor d=service.one(id);
        return new DoctorWithAllDataDTO(d);

    }

    @PutMapping("/{id}")
    com.example.Lab5.entity.DoctorWithIdDTO replaceDoctor(@Valid @RequestBody Doctor newDoctor, @PathVariable Long id){
        Doctor d= service.replaceDoctor(newDoctor,id);
        return new com.example.Lab5.entity.DoctorWithIdDTO(d);
    }

    @DeleteMapping("/{id}")
    void deleteDoctor(@PathVariable Long id){
        service.deleteDoctor(id);
    }

}
