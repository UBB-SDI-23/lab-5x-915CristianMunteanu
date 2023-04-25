package com.example.Lab5.repository;

import com.example.Lab5.entity.Hospital;
import com.example.Lab5.entity.HospitalDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {
}
