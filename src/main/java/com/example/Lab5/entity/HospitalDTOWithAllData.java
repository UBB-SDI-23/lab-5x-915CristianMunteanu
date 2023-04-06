package com.example.Lab5.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalDTOWithAllData {
    private Long id;
    private String name;

    private String address;

    private String phoneNumber;

    private int numberOfBeds;
    private List<com.example.Lab5.entity.DoctorWithIdDTO> doctors;
    public HospitalDTOWithAllData(Hospital hospital){
        id=hospital.getId();
        name=hospital.getName();
        address=hospital.getAddress();
        phoneNumber=hospital.getPhoneNumber();
        numberOfBeds=hospital.getNumberOfBeds();
        doctors = hospital.getDoctors().stream()
                .map(com.example.Lab5.entity.DoctorWithIdDTO::new).collect(Collectors.toList());

    }
}
