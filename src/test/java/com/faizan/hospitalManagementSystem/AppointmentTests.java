package com.faizan.hospitalManagementSystem;

import com.faizan.hospitalManagementSystem.entity.Appointment;
import com.faizan.hospitalManagementSystem.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTests {

    @Autowired
    AppointmentService appointmentService;

    @Test
    public void testCreateAppointment() {
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 0))
                .reason("fever")
                .build();

//        var newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);

//        System.out.println("newAppointment: " + newAppointment);

//        var updateAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(), 2L);
//
//        System.out.println("updateAppointment: " + updateAppointment);
    }
}
