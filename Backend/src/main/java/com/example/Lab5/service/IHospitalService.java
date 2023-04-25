package com.example.Lab5.service;

import com.example.Lab5.entity.Doctor;
import com.example.Lab5.entity.Hospital;
import com.example.Lab5.entity.HospitalDTO;
import com.example.Lab5.entity.LongDTO;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IHospitalService {
    List<HospitalDTO> all();
    Hospital newHospital(Hospital newHospital);
    Hospital one(Long id);
    Hospital replaceHospital(Hospital newHospital,Long id);
    void deleteHospital(Long id);

    List<Pair<Hospital, Doctor>> orderHospitalsByHighestDoctorSalary();

    void bulkAddDoctors(List<LongDTO> ids ,Long id);
}
