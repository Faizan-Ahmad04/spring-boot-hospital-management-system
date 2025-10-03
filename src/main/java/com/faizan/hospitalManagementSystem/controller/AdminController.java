package com.faizan.hospitalManagementSystem.controller;

import com.faizan.hospitalManagementSystem.dto.DoctorResponseDto;
import com.faizan.hospitalManagementSystem.dto.OnboardDoctorRequestDto;
import com.faizan.hospitalManagementSystem.dto.PatientResponseDto;
import com.faizan.hospitalManagementSystem.service.DoctorService;
import com.faizan.hospitalManagementSystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PatientService patientService;
    private final DoctorService doctorService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatient(
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
    ) {

        return ResponseEntity.ok(patientService.getAllPatient(pageNumber, pageSize));
    }

    public ResponseEntity<DoctorResponseDto> onBoardNewDoctor(@RequestBody OnboardDoctorRequestDto onboardDoctorRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.onBoardNewDoctor(onboardDoctorRequestDto));
    }
}
