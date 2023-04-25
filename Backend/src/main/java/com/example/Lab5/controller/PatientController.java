package com.example.Lab5.controller;

import com.example.Lab5.entity.Patient;
import com.example.Lab5.entity.PatientDTO;
import com.example.Lab5.entity.PatientWithDoctorDataDTO;
import com.example.Lab5.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    List<PatientDTO> all(){ return patientService.all().stream().map(PatientDTO::new).collect(Collectors.toList());}

    @PostMapping
    Patient newPatient(@RequestBody Patient newPatient){
        return patientService.newPatient(newPatient);
    }

    @GetMapping("/{id}")
    PatientWithDoctorDataDTO one(@PathVariable Long id){
        return new PatientWithDoctorDataDTO(patientService.one(id));
    }

    @PutMapping("/{id}")
    Patient replacePatient(@RequestBody Patient newPatient,@PathVariable Long id){
        return patientService.replacePatient(newPatient,id);
    }

    @DeleteMapping("/{id}")
    void deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
    }

}
