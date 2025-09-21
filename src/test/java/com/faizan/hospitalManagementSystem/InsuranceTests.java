package com.faizan.hospitalManagementSystem;

import com.faizan.hospitalManagementSystem.entity.Insurance;
import com.faizan.hospitalManagementSystem.entity.Patient;
import com.faizan.hospitalManagementSystem.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void testInsurance() {
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030, 12, 12))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);

        System.out.println("patient with insurance: " + patient);

        Patient newPatientWithNoInsurance = insuranceService.disaccociateInsuranceFromPatient(1L);

        System.out.println("newPatientWithNoInsurance: " + newPatientWithNoInsurance);
    }
}
