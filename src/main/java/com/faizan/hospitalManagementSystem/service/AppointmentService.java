package com.faizan.hospitalManagementSystem.service;


import com.faizan.hospitalManagementSystem.dto.AppointmentResponseDto;
import com.faizan.hospitalManagementSystem.dto.CreateAppointmentRequestDto;
import com.faizan.hospitalManagementSystem.entity.Appointment;
import com.faizan.hospitalManagementSystem.entity.Doctor;
import com.faizan.hospitalManagementSystem.entity.Patient;
import com.faizan.hospitalManagementSystem.repository.AppointmentRepository;
import com.faizan.hospitalManagementSystem.repository.DoctorRepository;
import com.faizan.hospitalManagementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    // Run using test for query
//    @Transactional
//    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {
//        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
//        Patient patient = patientRepository.findById(patientId).orElseThrow();
//
//        if (appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have ID");
//
//        appointment.setPatient(patient);
//        appointment.setDoctor(doctor);
//
//        patient.getAppointments().add(appointment);
//
//        return appointmentRepository.save(appointment);
//    }

    @Transactional
    @Secured("ROLE_PATIENT")
    public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto) {
        Long doctorId = createAppointmentRequestDto.getDoctorId();
        Long patientId = createAppointmentRequestDto.getPatientId();

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        Appointment appointment = Appointment.builder()
                .reason(createAppointmentRequestDto.getReason())
                .appointmentTime(createAppointmentRequestDto.getAppointmentTime())
                .build();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);

        appointment = appointmentRepository.save(appointment);

        return modelMapper.map(appointment, AppointmentResponseDto.class);
    }

    @Transactional
    @PreAuthorize("hasAuthority('appointment:write') OR #doctorId == authentication.principal.id")
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with id: " + appointmentId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));

        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment);

        return appointment;
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('DOCTOR) AND #doctorIid == authentication.principal.id)")
    public List<AppointmentResponseDto> getAllAppointmentsOfDoctor(long doctorIid) {
        Doctor doctor = doctorRepository.findById(doctorIid)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorIid));

        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }
}
