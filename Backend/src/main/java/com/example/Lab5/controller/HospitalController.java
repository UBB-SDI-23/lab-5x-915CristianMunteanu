package com.example.Lab5.controller;

import com.example.Lab5.entity.*;
import com.example.Lab5.service.IHospitalService;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/hospitals")
public class HospitalController {
    private final IHospitalService service;

    public HospitalController(IHospitalService service) {
        this.service = service;

    }

    @GetMapping
    List<HospitalDTO> all(){
        return service.all();
    }

    @GetMapping("/filter")
    List<HospitalWithDoctorSalaryDTO> orderHospitalsByLowestDoctorSalary(){
        List<Pair<Hospital, Doctor>> result=service.orderHospitalsByHighestDoctorSalary();
        List<HospitalWithDoctorSalaryDTO> toReturn=new ArrayList<>();
        for (Pair<Hospital, Doctor> res:result) {
            toReturn.add(new HospitalWithDoctorSalaryDTO(res.getFirst(), res.getSecond().getSalary()));
        }
        return toReturn;
    }

    @PostMapping("/{id}")
    void bulkAddDoctors(@RequestBody List<LongDTO> ids,@PathVariable Long id){
        service.bulkAddDoctors(ids,id);
    }

    @PostMapping
    Hospital newHospital(@RequestBody Hospital newHospital){
        return service.newHospital(newHospital);
    }

    @GetMapping("/{id}")
    HospitalDTOWithAllData one(@PathVariable Long id){
        Hospital h= service.one(id);
        return new HospitalDTOWithAllData(h);

    }

    @PutMapping("/{id}")
    Hospital replaceHospital(@RequestBody Hospital newHospital,@PathVariable Long id){
        return service.replaceHospital(newHospital,id);
    }

    @DeleteMapping("/{id}")
    void deleteHospital(@PathVariable Long id){
        service.deleteHospital(id);
    }
}
