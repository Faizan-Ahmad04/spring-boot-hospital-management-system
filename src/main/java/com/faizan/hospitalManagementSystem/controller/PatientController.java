package com.faizan.hospitalManagementSystem.controller;

import com.faizan.hospitalManagementSystem.dto.AppointmentResponseDto;
import com.faizan.hospitalManagementSystem.dto.CreateAppointmentRequestDto;
import com.faizan.hospitalManagementSystem.dto.PatientResponseDto;
import com.faizan.hospitalManagementSystem.service.AppointmentService;
import com.faizan.hospitalManagementSystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    @CacheEvict(cacheNames = "appointments", key = "#createAppointmentRequestDto.doctorId")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    @GetMapping("/profile/{patientId}")
    @Cacheable(cacheNames = "patient", key = "#patientId")
    public ResponseEntity<PatientResponseDto> getPatientProfile (@PathVariable Long patientId) {
        return ResponseEntity.ok(patientService.getPatientBYId(patientId));
    }
}
