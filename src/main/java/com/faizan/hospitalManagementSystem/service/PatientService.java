package com.faizan.hospitalManagementSystem.service;

import com.faizan.hospitalManagementSystem.dto.PatientResponseDto;
import com.faizan.hospitalManagementSystem.entity.Patient;
import com.faizan.hospitalManagementSystem.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PatientResponseDto getPatientBYId(Long id) {

        Patient patient = patientRepository.findById(id).orElseThrow();

//        Patient p2 = patientRepository.findById(id).orElseThrow();
//
//        System.out.println(p1 == p2);
//
//        p1.setName("YOYO");

        return modelMapper.map(patient, PatientResponseDto.class);

    }

    public List<PatientResponseDto> getAllPatient(Integer pageNumber, Integer pageSize) {
        return patientRepository.findAllPatients(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(patient -> modelMapper.map(patient, PatientResponseDto.class))
                .collect(Collectors.toList());
    }
}
