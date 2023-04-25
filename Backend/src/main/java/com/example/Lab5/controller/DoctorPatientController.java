package com.example.Lab5.controller;

import com.example.Lab5.entity.DoctorPatient;
import com.example.Lab5.entity.DoctorPatientDTO;
import com.example.Lab5.service.DoctorPatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/doctorceva")
public class DoctorPatientController {
    private DoctorPatientService doctorPatientService;

    public DoctorPatientController(DoctorPatientService doctorPatientService) {
        this.doctorPatientService = doctorPatientService;
    }

    @GetMapping
    public List<DoctorPatientDTO> all(){return doctorPatientService.all().stream().map(DoctorPatientDTO::new).collect(Collectors.toList());}

    @PostMapping("/doctor/{doctorId}/patient/{patientId}")
    public void save(@RequestBody DoctorPatient newDoctorPatient,@PathVariable Long doctorId,@PathVariable Long patientId){
        doctorPatientService.addRelationship(newDoctorPatient,doctorId,patientId);
    }

}
