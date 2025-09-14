package com.faizan.hospitalManagementSystem.service;

import com.faizan.hospitalManagementSystem.entity.Patient;
import com.faizan.hospitalManagementSystem.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public Patient getPatientBYId(Long id) {

        Patient p1 = patientRepository.findById(id).orElseThrow();

        Patient p2 = patientRepository.findById(id).orElseThrow();

        System.out.println(p1 == p2);

        p1.setName("YOYO");

        return p1;

    }
}
