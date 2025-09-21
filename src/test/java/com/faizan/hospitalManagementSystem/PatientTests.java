package com.faizan.hospitalManagementSystem;

import com.faizan.hospitalManagementSystem.dto.BloodGroupCountResponseDto;
import com.faizan.hospitalManagementSystem.entity.Patient;
import com.faizan.hospitalManagementSystem.entity.type.BloodGroupType;
import com.faizan.hospitalManagementSystem.repository.PatientRepository;
import com.faizan.hospitalManagementSystem.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository() {

//        List<Patient> patientList = patientRepository.findAll();
        List<Patient> patientList = patientRepository.findAllPatientWithAppointment();
        System.out.println("All patients: " + patientList);



    }

    @Test
    public void testTransactionMethods() {

//        Patient patient = patientService.getPatientBYId(1L);
//
//        System.out.println("Patient: " + patient);


//        Patient patient = patientRepository.findByName("Diya Patel");
//
//        System.out.println("patient: " + patient);


//        List<Patient> patientList = patientRepository.findByBirthDateOrEmail(LocalDate.of(1988, 3, 15), "diya"+
//                ".patel@example.com");
//
//        for (Patient patient: patientList) {
//            System.out.println("Patient: " + patient);
//        }


//        List<Patient> patientList = patientRepository.findByBirthDateBetween(
//                LocalDate.of(1990, 1, 1),
//                LocalDate.of(1995, 12, 31)
//        );
//
//        for (Patient patient: patientList) {
//            System.out.println("Patient: " + patient);
//        }


//
//        List<Patient> patientList = patientRepository.findByNameContainingOrderByIdDesc("Di");
//
//        for (Patient patient: patientList) {
//            System.out.println("Patient: " + patient);
//        }


//        List<Patient> patientList = patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);
//
//        for (Patient patient: patientList) {
//            System.out.println("Patient: " + patient);
//        }


//        List<Patient> patientList = patientRepository.findByBornAfter(LocalDate.of(1993, 3, 14));
//
//        for (Patient patient: patientList) {
//            System.out.println("Patient: " + patient);
//        }


//        List<BloodGroupCountResponseDto> bloodGroupCountList = patientRepository.countEachBloodGroupType();
//
//        for (BloodGroupCountResponseDto bloodGroupCount: bloodGroupCountList) {
//            System.out.println("Blood group count: " + bloodGroupCount);
//        }


//        Page<Patient> patientList = patientRepository.findAllPatients(PageRequest.of(1,3, Sort.by("name")));
//
//        for (Patient patient: patientList) {
//            System.out.println("Patient: " + patient);
//        }

        int rowsUpdated = patientRepository.updateNameWithId("Arav Sharma", 1L);
        System.out.println("rowsUpdated" + rowsUpdated);

    }

}
